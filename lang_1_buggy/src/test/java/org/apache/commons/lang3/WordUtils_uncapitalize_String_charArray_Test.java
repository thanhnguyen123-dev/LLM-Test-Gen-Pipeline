package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_uncapitalize_String_charArray_Test {

    @Before
    public void setUp() {
        // Any necessary setup can be done here
    }

    @Test
    public void testUncapitalize_NullString() {
        assertNull("Expected null when input string is null",
                WordUtils.uncapitalize(null, new char[] {'.'}));
    }

    @Test
    public void testUncapitalize_EmptyString() {
        assertEquals("Expected empty string when input string is empty",
                "", WordUtils.uncapitalize("", new char[] {'.'}));
    }

    @Test
    public void testUncapitalize_NoDelimiters() {
        assertEquals("Expected input string when delimiters are null",
                "I AM FINE", WordUtils.uncapitalize("I AM FINE", null));
    }

    @Test
    public void testUncapitalize_EmptyDelimiters() {
        assertEquals("Expected input string when delimiters are empty",
                "I AM FINE", WordUtils.uncapitalize("I AM FINE", new char[0]));
    }

    @Test
    public void testUncapitalize_WithDelimiters() {
        assertEquals("Expected specific uncapitalization with delimiters",
                "i AM.fINE", WordUtils.uncapitalize("I AM.FINE", new char[] {'.'}));
    }

    @Test
    public void testUncapitalize_NoDelimiters_NonWhitespaceChars() {
        assertEquals("Expected specific uncapitalization with delimiters",
                "i am fine", WordUtils.uncapitalize("I am fine", new char[] {' '}));
    }

    @Test
    public void testUncapitalize_MixedDelimiters() {
        assertEquals("Expected specific uncapitalization with mixed delimiters",
                "i am.fine", WordUtils.uncapitalize("I AM.FINE", new char[] {'.', ' '}));
    }
}
