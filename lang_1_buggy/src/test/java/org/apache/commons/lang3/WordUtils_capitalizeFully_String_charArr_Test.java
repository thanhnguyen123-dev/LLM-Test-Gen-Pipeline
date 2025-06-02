package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_capitalizeFully_String_charArr_Test {

    @Before
    public void setUp() {
        // Setup if necessary
    }

    @Test
    public void testNullInputString() {
        assertNull(WordUtils.capitalizeFully(null, new char[] {'.'}));
    }

    @Test
    public void testEmptyInputString() {
        assertEquals("", WordUtils.capitalizeFully("", new char[] {'.'}));
    }

    @Test
    public void testNullDelimiters() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("i aM fine", null));
    }

    @Test
    public void testEmptyDelimiters() {
        assertEquals("I am Fine", WordUtils.capitalizeFully("i aM fine", new char[0]));
    }

    @Test
    public void testCapitalizeWithGivenDelimiters() {
        assertEquals("I am.Fine", WordUtils.capitalizeFully("i aM.fine", new char[] {'.'}));
    }

    @Test
    public void testCapitalizeWithWhitespaceDelimiter() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("i aM fine", new char[] {' '}));
    }

    @Test
    public void testCapitalizeWithMultipleDelimiters() {
        assertEquals("I Am.Fine", WordUtils.capitalizeFully("i aM.fine", new char[] {' ', '.'}));
    }

    @Test
    public void testCapitalizeWithNoDelimiterMatch() {
        assertEquals("I aM Fine", WordUtils.capitalizeFully("i aM fine", new char[] {'-'}));
    }
}
