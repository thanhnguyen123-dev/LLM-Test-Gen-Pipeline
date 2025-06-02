package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_String_capitalize_String_Test {

    @Test
    public void testCapitalize_NullString() {
        assertNull(WordUtils.capitalize(null));
    }

    @Test
    public void testCapitalize_EmptyString() {
        assertEquals("", WordUtils.capitalize(""));
    }

    @Test
    public void testCapitalize_SingleWord() {
        assertEquals("Hello", WordUtils.capitalize("hello"));
    }

    @Test
    public void testCapitalize_MultipleWords() {
        assertEquals("I Am FINE", WordUtils.capitalize("i am FINE"));
    }

    @Test
    public void testCapitalize_SingleLowercaseLetters() {
        assertEquals("A B C", WordUtils.capitalize("a b c"));
    }

    @Test
    public void testCapitalize_Punctuation() {
        assertEquals("Hello, World!", WordUtils.capitalize("hello, world!"));
    }

    @Test
    public void testCapitalize_AllUppercase() {
        assertEquals("HELLO WORLD", WordUtils.capitalize("HELLO WORLD"));
    }

    @Test
    public void testCapitalize_MixedCase() {
        assertEquals("Java Programming", WordUtils.capitalize("java PROGRAMMING"));
    }

    @Test
    public void testCapitalize_Whitespace() {
        assertEquals("   A", WordUtils.capitalize("   a"));
    }
}
