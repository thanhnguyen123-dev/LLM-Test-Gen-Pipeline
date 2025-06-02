package org.apache.commons.lang3;

import org.apache.commons.lang3.text.translate.CodePointTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CodePointTranslator_int_translate_CharSequence_int_Writer_Test {

    private CodePointTranslator codePointTranslator;

    @Before
    public void setUp() {
        codePointTranslator = new CodePointTranslator();
    }

    @Test
    public void testTranslateTypicalCase() throws Exception {
        String input = "abcdef";
        Writer out = new StringWriter();
        int result = codePointTranslator.translate(input, 0, out);
        assertEquals(1, result);
    }

    @Test
    public void testTranslateEndIndex() throws Exception {
        String input = "abcdef";
        Writer out = new StringWriter();
        int result = codePointTranslator.translate(input, 5, out);
        assertEquals(1, result);
    }

    @Test
    public void testTranslateEmptyInput() throws Exception {
        String input = "";
        Writer out = new StringWriter();
        int result = codePointTranslator.translate(input, 0, out);
        assertEquals(0, result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testTranslateIndexOutOfBounds() throws Exception {
        String input = "abcd";
        Writer out = new StringWriter();
        codePointTranslator.translate(input, 10, out);
    }

    @Test
    public void testTranslateValidCodepointBoundary() throws Exception {
        String input = new String(Character.toChars(Character.MAX_CODE_POINT));
        Writer out = new StringWriter();
        int result = codePointTranslator.translate(input, 0, out);
        assertEquals(1, result);
    }

    @Test
    public void testTranslateSingleCharacter() throws Exception {
        String input = "a";
        Writer out = new StringWriter();
        int result = codePointTranslator.translate(input, 0, out);
        assertEquals(1, result);
    }
}
