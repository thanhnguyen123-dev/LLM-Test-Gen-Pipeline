package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_capitalize_String_charArray_Test {

    private char[] defaultDelimiters;

    @Before
    public void setUp() {
        // Set up default delimiters
        defaultDelimiters = new char[]{' '};
    }

    @Test
    public void testCapitalize_NullInput() {
        assertNull(WordUtils.capitalize(null, defaultDelimiters));
    }

    @Test
    public void testCapitalize_EmptyString() {
        assertEquals("", WordUtils.capitalize("", defaultDelimiters));
    }

    @Test
    public void testCapitalize_NoDelimiters() {
        assertEquals("I Am Fine", WordUtils.capitalize("i am fine", null));
    }

    @Test
    public void testCapitalize_WithDelimiters() {
        assertEquals("I aM.Fine", WordUtils.capitalize("i aM.fine", new char[]{'.'}));
    }

    @Test
    public void testCapitalize_MixedDelimiters() {
        assertEquals("Hello-World", WordUtils.capitalize("hello-world", new char[]{'-'}));
    }

    @Test
    public void testCapitalize_SingleCharacter() {
        assertEquals("I", WordUtils.capitalize("i", defaultDelimiters));
    }

    @Test
    public void testCapitalize_MultipleSpaces() {
        assertEquals("A  B C", WordUtils.capitalize("a  b c", new char[]{' '}));
    }

    @Test
    public void testCapitalize_SpecialCharacters() {
        assertEquals("!Hi!There", WordUtils.capitalize("!hi!there", new char[]{'!'}));
    }

    @Test
    public void testCapitalize_LeadingTrailingSpaces() {
        assertEquals(" Abc ", WordUtils.capitalize(" abc ", new char[]{' '}));
    }

    @Test
    public void testCapitalize_AllUpperCaseInput() {
        assertEquals("Hello World", WordUtils.capitalize("HELLO WORLD", defaultDelimiters));
    }

    @Test
    public void testCapitalize_NoDelimitersInString() {
        assertEquals("Abc", WordUtils.capitalize("abc", new char[]{'.'}));
    }
}
