package org.apache.commons.lang3;

import org.junit.Test;
import org.apache.commons.lang3.text.WordUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WordUtils_capitalize_String_charArray_Test {

    @Test
    public void testCapitalizeWithNullString() {
        assertNull(WordUtils.capitalize(null, null));
    }

    @Test
    public void testCapitalizeWithEmptyString() {
        assertEquals("", WordUtils.capitalize("", null));
    }

    @Test
    public void testCapitalizeWithoutDelimiters() {
        assertEquals("I Am Fine", WordUtils.capitalize("i am fine", null));
    }

    @Test
    public void testCapitalizeWithSpecifiedDelimiters() {
        assertEquals("I aM.Fine", WordUtils.capitalize("i aM.fine", new char[]{'.'}));
    }

    @Test
    public void testCapitalizeWithEmptyDelimiters() {
        assertEquals("I Am Fine", WordUtils.capitalize("i am fine", new char[]{}));
    }

    @Test
    public void testCapitalizeWithSingleCharacterWord() {
        assertEquals("A B C", WordUtils.capitalize("a b c", null));
    }

    @Test
    public void testCapitalizeWithMixedCase() {
        assertEquals("Java Is Great", WordUtils.capitalize("java is Great", null));
    }

    @Test
    public void testCapitalizeWithNonAlphabeticFirstCharacter() {
        assertEquals("123 Java Test", WordUtils.capitalize("123 java test", null));
    }

    @Test
    public void testCapitalizeWithAllDelimiters() {
        assertEquals("Java;Is!Great", WordUtils.capitalize("java;is!great", new char[]{';', '!'}));
    }
}
