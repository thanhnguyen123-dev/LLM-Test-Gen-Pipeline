package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class WordUtils_capitalize_String_charArray_Test {

    private WordUtils wordUtils;

    @Before
    public void setUp() {
        wordUtils = new WordUtils();
    }

    @Test
    public void testCapitalize_NullInput() {
        assertNull(WordUtils.capitalize(null, new char[]{'.'}));
    }

    @Test
    public void testCapitalize_EmptyString() {
        assertEquals("", WordUtils.capitalize("", new char[]{' '}));
    }

    @Test
    public void testCapitalize_NoDelimiters() {
        assertEquals("I Am Fine", WordUtils.capitalize("i am fine", null));
    }

    @Test
    public void testCapitalize_SingleCharacterDelimiters() {
        assertEquals("I AM.Fine", WordUtils.capitalize("i aM.fine", new char[]{'.'}));
    }

    @Test
    public void testCapitalize_MixedDelimiters() {
        assertEquals("I Am, Fine!", WordUtils.capitalize("i am, fine!", new char[]{',', ' '}));
    }

    @Test
    public void testCapitalize_MultipleConsecutiveDelimiters() {
        assertEquals("I  Am", WordUtils.capitalize("i  am", new char[]{' '}));
    }

    @Test
    public void testCapitalize_NumericString() {
        assertEquals("123 a", WordUtils.capitalize("123 a", new char[]{' '}));
    }

    @Test
    public void testCapitalize_AllUpperCase() {
        assertEquals("I AM Fine", WordUtils.capitalize("I AM fine", new char[]{' '}));
    }

    @Test
    public void testCapitalize_AllLowerCase() {
        assertEquals("I Am Fine", WordUtils.capitalize("i am fine", new char[]{' '}));
    }

    @Test
    public void testCapitalize_EmptyDelimiters() {
        assertEquals("Test", WordUtils.capitalize("test", new char[]{}));
    }
}
