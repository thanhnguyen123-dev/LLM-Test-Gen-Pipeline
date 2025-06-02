package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_String_capitalize_String_Test {

    @Test
    public void testCapitalizeNull() {
        assertNull(org.apache.commons.lang3.text.WordUtils.capitalize(null));
    }

    @Test
    public void testCapitalizeEmptyString() {
        assertEquals("", org.apache.commons.lang3.text.WordUtils.capitalize(""));
    }

    @Test
    public void testCapitalizeSingleWord() {
        assertEquals("Hello", org.apache.commons.lang3.text.WordUtils.capitalize("hello"));
    }

    @Test
    public void testCapitalizeMultipleWords() {
        assertEquals("I Am Fine", org.apache.commons.lang3.text.WordUtils.capitalize("i am fine"));
    }

    @Test
    public void testCapitalizeMixedCase() {
        assertEquals("I Am FINE", org.apache.commons.lang3.text.WordUtils.capitalize("i am FINE"));
    }

    @Test
    public void testCapitalizeLeadingWhitespace() {
        assertEquals(" Leading Whitespace", org.apache.commons.lang3.text.WordUtils.capitalize(" leading whitespace"));
    }

    @Test
    public void testCapitalizeTrailingWhitespace() {
        assertEquals("Trailing Whitespace ", org.apache.commons.lang3.text.WordUtils.capitalize("trailing whitespace "));
    }

    @Test
    public void testCapitalizeWhitespaceBetweenWords() {
        assertEquals("Whitespace Between Words", org.apache.commons.lang3.text.WordUtils.capitalize("whitespace between words"));
    }
}
