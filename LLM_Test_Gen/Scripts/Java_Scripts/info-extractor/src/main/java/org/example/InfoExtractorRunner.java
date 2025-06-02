package org.example;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import sootup.core.Project;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.model.*;
import sootup.core.types.ClassType;
import sootup.core.views.View;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaProject;
import sootup.java.core.JavaSootClass;
import sootup.java.core.JavaSootClassSource;
import sootup.java.core.language.JavaLanguage;
import sootup.core.types.Type;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.DoStmt;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;


import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InfoExtractorRunner {
    private static final String TEST_DATA_RELATIVE_PATH = "../../../Data/Test_Data.csv";
    private static final String LANG_1_BUGGY_SRC_RELATIVE_PATH = "../../../../lang_1_buggy/src/main/java";
    private static final String CSV_DELIMITER = ",";
    private static final String[] CSV_COLUMNS = {
            "FQN",
            "Signature",
            "Jimple Code Representation",
            "Is Constructor",
            "Method Modifiers",
            "Annotations",
            "Java Doc",
            "Class Context",
            "Class Fields",
            "Loop Count",
            "Branch Count",
            "External Dependencies",
            "Literal Constants",
            "Constructor Visibility",
            "Class Factory Methods"
    };

    private static final String CSV_HEADER = InfoExtractorRunner.getCsvHeader();
    private static final JavaParser parser = createJavaParser();
    public static void main(String[] args) {
        String targetClassesPathToLang1Buggy = "../../../../lang_1_buggy/target/classes";

        Path moduleBasePath = Paths.get(".").toAbsolutePath().normalize();
        Path outputCsvAbsPath = moduleBasePath.resolve(TEST_DATA_RELATIVE_PATH).normalize();

        // Point SootUp to the compiled classes
        AnalysisInputLocation<JavaSootClass> inputLocation =
                new JavaClassPathAnalysisInputLocation(targetClassesPathToLang1Buggy);

        // Build the project (using Java 8 here)
        JavaLanguage language = new JavaLanguage(8);
        Project project = JavaProject.builder(language)
                .addInputLocation(inputLocation)
                .build();

        // Create a View for reading class/method data
        View view = project.createView();

        // Retrieve all classes discovered by SootUp
        Collection<SootClass<JavaSootClassSource>> allClasses = view.getClasses();

        // Define the targeted classes
        Set<String> targetClassesNames = Set.of(
                "org.apache.commons.lang3.CharRange",
                "org.apache.commons.lang3.CharSetUtils",
                "org.apache.commons.lang3.text.WordUtils",
                "org.apache.commons.lang3.text.translate.CodePointTranslator",
                "org.apache.commons.lang3.concurrent.AtomicInitializer"
        );


        // Iterate over each class
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputCsvAbsPath.toString())))  {
            writer.println(CSV_HEADER);

            for (SootClass<JavaSootClassSource> sootClass : allClasses) {
                String fullClassName = sootClass.getName();

                // If this is the runner class or the methods isn't from CharRange, CharSetUtils, or WordUtils, skip it
                if (fullClassName.equals("org.example.InfoExtractorRunner") || !targetClassesNames.contains(fullClassName)) {
                    continue;
                }

                // Iterate over each method in the class
                for (SootMethod method : sootClass.getMethods()) {
                    // Only proceed if the method is concrete
                    if (!method.isConcrete() || method.getName().equals("<clinit>") || method.getName().startsWith("access$")) {
                        continue;
                    }

                    // Get method name, parameter types, and return type
                    String methodName = method.getName();
                    List<Type> paramTypes = method.getParameterTypes();
                    Type returnType = method.getReturnType();

                    String paramListString = paramTypes.stream()
                            .map(Type::toString)
                            .collect(Collectors.joining(", "));

                    StringBuilder fqnStringBuilder = new StringBuilder();
                    StringBuilder signatureStringBuilder = new StringBuilder();


                    // Set up configuration for JavaParser to parse the source file
                    String classPath = fullClassName.replace('.', File.separatorChar);
                    String simpleClassName = sootClass.getName().substring(sootClass.getName().lastIndexOf('.') + 1);
                    Path srcPath = Paths.get(
                            LANG_1_BUGGY_SRC_RELATIVE_PATH,
                            classPath + ".java"
                    );

                    CompilationUnit compilationUnit = parser.parse(srcPath)
                            .getResult()
                            .orElseThrow(() -> new RuntimeException("Unable to parse: " + srcPath));

                    ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit
                            .getClassByName(simpleClassName)
                            .orElseThrow(() -> new RuntimeException("Class not found: " + sootClass.getName()));

                    Integer paramCount = paramTypes.size();

                    CallableDeclaration<?> methodDeclaration;

                    if (methodName.equals("<init>")) {
                        List<ConstructorDeclaration> constructorDeclarationList = classOrInterfaceDeclaration.getConstructors()
                                .stream()
                                .filter(c -> c.getParameters().size() == paramCount)
                                .collect(Collectors.toList());
                        if (constructorDeclarationList.isEmpty()) continue;
                        methodDeclaration = constructorDeclarationList.get(0);
                    }

                    else {
                        methodDeclaration = classOrInterfaceDeclaration.getMethodsByName(methodName)
                                .stream()
                                .filter(m ->
                                        m.getParameters().size() == method.getParameterCount()
                                )
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Method not found: " + methodName));
                    }

                    String constructorVisibility;
                    if (methodDeclaration instanceof ConstructorDeclaration) {
                        ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration) methodDeclaration;
                        if (constructorDeclaration.isPublic()) {
                            constructorVisibility = "public";
                        } else if (constructorDeclaration.isProtected()) {
                            constructorVisibility = "protected";
                        } else if (constructorDeclaration.isPrivate()) {
                            constructorVisibility = "private";
                        } else {
                            constructorVisibility = "package-private"; // package-private
                        }
                    } else {
                        List<ConstructorDeclaration> allConstructors = classOrInterfaceDeclaration.getConstructors();
                        if (allConstructors.isEmpty()) {
                            constructorVisibility = "public";
                        } else {
                            boolean anyPublic = allConstructors.stream().anyMatch(ConstructorDeclaration::isPublic);
                            boolean anyProtected = allConstructors.stream().anyMatch(ConstructorDeclaration::isProtected);
                            boolean anyPrivate = allConstructors.stream().anyMatch(ConstructorDeclaration::isPrivate);
                            if (anyPublic) {
                                constructorVisibility = "public";
                            } else if (anyProtected) {
                                constructorVisibility = "protected";
                            } else if (anyPrivate) {
                                constructorVisibility = "private";
                            } else {
                                constructorVisibility = "package-private"; // package-private
                            }
                        }
                    }

                    // Get the FQN
                    String methodNameToUse = methodName.equals("<init>") ? simpleClassName : methodName;

                    fqnStringBuilder
                            .append(fullClassName)
                            .append(".")
                            .append(methodNameToUse)
                            .append("(")
                            .append(paramListString)
                            .append(")");

                    // Get the signature
                    signatureStringBuilder
                            .append(returnType)
                            .append(" ")
                            .append(methodNameToUse)
                            .append("(")
                            .append(paramListString)
                            .append(")");

                    // Get the Jimple code
                    String jimpleCode = method.getBody().toString();

                    // Get the modifiers from the Soot Method
                    String modifiers = method.getModifiers().stream()
                            .map(Enum::name)
                            .collect(Collectors.joining(" "));

                    // Get annotations
                    String annotations = methodDeclaration.getAnnotations().stream()
                            .map(a -> a.getNameAsString())
                            .collect(Collectors.joining(", "));

                    // Get JavaDoc
                    String rawJavaDoc = methodDeclaration.getJavadoc()
                            .map(javadoc -> {
                                StringBuilder sb = new StringBuilder();
                                sb.append(javadoc.getDescription().toText().trim());

                                javadoc.getBlockTags().forEach(blockTag -> {
                                    sb.append(System.lineSeparator());
                                    sb.append("@").append(blockTag.getTagName());
                                    blockTag.getName().ifPresent(name -> sb.append(" ").append(name));
                                    sb.append(" ").append(blockTag.getContent().toText().trim());
                                });
                                return sb.toString();
                            }).orElse("");

                    // Get class context
                    String superName = "";
                    Optional<? extends ClassType> maybeSuper = sootClass.getSuperclass();
                    if (maybeSuper.isPresent()) {
                        ClassType classType = maybeSuper.get();
                        String fqn = classType.getFullyQualifiedName();
                        if (!fqn.equals("java.lang.Object")) {
                            superName = fqn;
                        }
                    }
                    List<String> interfaces = sootClass.getInterfaces().stream()
                            .map(ct -> ct.getFullyQualifiedName())
                            .collect(Collectors.toList());
                    String classContext;
                    if (!superName.isEmpty() && !interfaces.isEmpty()) {
                        classContext = superName + " implements " + String.join(", ", interfaces);
                    } else if (!superName.isEmpty()) {
                        classContext = superName;
                    } else if (!interfaces.isEmpty()) {
                        classContext = "implements " + String.join(", ", interfaces);
                    } else {
                        classContext = "";
                    }

                    // Get class fields
                    String classFields = sootClass.getFields().stream()
                            .map(field -> {
                                String fieldName = field.getName();
                                String fieldType = field.getType().toString();
                                String fieldModifiers = field.getModifiers().stream()
                                        .map(Enum::name)
                                        .collect(Collectors.joining(" "));
                                return fieldModifiers + " " + fieldType + " " + fieldName;
                            })
                            .collect(Collectors.joining("; "));

                    // Get branchCount
                    int ifCount = methodDeclaration.findAll(IfStmt.class).size();
                    int switchCount = methodDeclaration.findAll(SwitchStmt.class).size();
                    int branchCount = ifCount + switchCount;

                    int forCount = methodDeclaration.findAll(ForStmt.class).size();
                    int whileCount = methodDeclaration.findAll(WhileStmt.class).size();
                    int doCount = methodDeclaration.findAll(DoStmt.class).size();
                    int loopCount = forCount + whileCount + doCount;

                    // Get external dependencies
                    List<String> externalCalls = new ArrayList<>();
                    methodDeclaration.findAll(MethodCallExpr.class).forEach(call -> {
                        try {
                            ResolvedMethodDeclaration resolvedMethodDeclaration = call.resolve();
                            externalCalls.add(resolvedMethodDeclaration.getQualifiedSignature());
                        } catch (Exception e) {
                            // Ignore unresolved method calls
                        }
                    });
                    String externalCallsRaw = externalCalls.stream()
                            .distinct()
                            .collect(Collectors.joining(", "));

                    // Get literal constants
                    List<String> literalList = new ArrayList<>();
                    methodDeclaration.findAll(StringLiteralExpr.class).forEach(literal -> {
                        literalList.add("\"" + literal.getValue() + "\"");
                    });
                    methodDeclaration.findAll(CharLiteralExpr.class).forEach(charLiteral -> {
                        literalList.add("'" + charLiteral.getValue() + "'");
                    });
                    methodDeclaration.findAll(IntegerLiteralExpr.class).forEach(integer -> {
                        literalList.add(integer.getValue());
                    });
                    methodDeclaration.findAll(DoubleLiteralExpr.class).forEach(doubleLiteral -> {
                        literalList.add(doubleLiteral.getValue());
                    });
                    methodDeclaration.findAll(BooleanLiteralExpr.class).forEach(booleanLiteral -> {
                        literalList.add(String.valueOf(booleanLiteral.getValue()));
                    });

                    String literalsRaw = literalList.stream()
                            .distinct()
                            .collect(Collectors.joining(", "));

                    // Extract class factory method
                    List<MethodDeclaration> allMethods = classOrInterfaceDeclaration.getMethods();
                    List<String> factoryList = allMethods.stream()
                            .filter(m -> m.isPublic() && m.isStatic() && m.getType().asString().equals(classOrInterfaceDeclaration.getNameAsString()))
                            .map(m -> {
                                String currentMethodName = m.getNameAsString();
                                String paramList = m.getParameters().stream()
                                        .map(param -> param.getType().asString())
                                        .collect(Collectors.joining(","));
                                return currentMethodName + "(" + paramList + ")";
                            })
                            .collect(Collectors.toList());
                    String classFactoryMethods = String.join(", ", factoryList);


                    // Construct a CSV line
                    String csvLine = String.join(
                            CSV_DELIMITER,
                            quoteField(fqnStringBuilder.toString()),
                            quoteField(signatureStringBuilder.toString()),
                            quoteField(jimpleCode),
                            quoteField(String.valueOf(methodName.equals("<init>"))),
                            quoteField(modifiers),
                            quoteField(annotations),
                            quoteField(rawJavaDoc),
                            quoteField(classContext),
                            quoteField(classFields),
                            quoteField(String.valueOf(loopCount)),
                            quoteField(String.valueOf(branchCount)),
                            quoteField(externalCallsRaw),
                            quoteField(literalsRaw),
                            quoteField(constructorVisibility),
                            quoteField(classFactoryMethods)
                    );
                    writer.println(csvLine);

                    // Print the information in terminal (for fast debugging)
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("CODE INFORMATION:\n");
                    System.out.println("- FQN: " + fqnStringBuilder);
                    System.out.println("- Signature: " + signatureStringBuilder);
                    System.out.println("- Jimple Code:\n" + jimpleCode);
                    System.out.println("- Method Modifiers: " + modifiers);
                    System.out.println("- Annotations: " + annotations);
                    System.out.println("- JavaDoc: " + rawJavaDoc);
                    System.out.println("- Class Context: " + classContext);
                    System.out.println("- Class Fields: " + classFields);
                    System.out.println("- Loop Count: " + loopCount);
                    System.out.println("- Branch Count: " + branchCount);
                    System.out.println("- External Dependencies: " + externalCallsRaw);
                    System.out.println("- Literal Constants: " + literalsRaw);
                    System.out.println("- Constructor Visibility: " + constructorVisibility);
                    System.out.println("- Class Factory Methods: " + classFactoryMethods);

                    System.out.println("-----------------------------------------------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while processing the classes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String quoteField(String data) {
        if (data == null) {
            return "\"\"";
        }
        String escapedData = data.replace("\"", "\"\"");
        return "\"" + escapedData + "\"";
    }

    private static String getCsvHeader() {
        return Stream.of(CSV_COLUMNS)
                .map(col -> "\"" + col + "\"")
                .collect(Collectors.joining(CSV_DELIMITER));
    }

    private static JavaParser createJavaParser() {
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new ReflectionTypeSolver());
        typeSolver.add(new JavaParserTypeSolver((new File(LANG_1_BUGGY_SRC_RELATIVE_PATH))));

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);

        ParserConfiguration config = new ParserConfiguration().setSymbolResolver(symbolSolver);

        return new JavaParser(config);

    }
}
