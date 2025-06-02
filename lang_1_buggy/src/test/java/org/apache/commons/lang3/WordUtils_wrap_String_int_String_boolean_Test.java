package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_wrap_String_int_String_boolean_Test {

    @Before
    public void setUp() {
        // No setup required as method is static and requires no external state.
    }

    @Test
    public void testWrapNullString() {
        assertNull(WordUtils.wrap(null, 10, "\n", true));
    }

    @Test
    public void testWrapEmptyString() {
        assertEquals("", WordUtils.wrap("", 10, "\n", true));
    }

    @Test
    public void testWrapSingleWord() {
        assertEquals("hello", WordUtils.wrap("hello", 10, "\n", true));
    }

    @Test
    public void testWrapNoWrapRequired() {
        String input = "This is a test";
        assertEquals(input, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrapBasicWrap() {
        String input = "This is a simple test";
        String expected = "This is a\nsimple test";
        assertEquals(expected, WordUtils.wrap(input, 10, "\n", true));
    }

    @Test
    public void testWrapWithLongWords() {
        String input = "averylongwordthatexceedslinelength";
        String expected = "averylong\nwordthatex\nceedslinel\nength";
        assertEquals(expected, WordUtils.wrap(input, 10, "\n", true));
    }

    @Test
    public void testWrapWithoutWrappingLongWords() {
        String input = "averylongwordthatexceedslinelength";
        assertEquals(input, WordUtils.wrap(input, 10, "\n", false));
    }

    @Test
    public void testWrapWithNullNewLineStr() {
        String input = "This is a simple test";
        String expected = "This is a" + System.lineSeparator() + "simple test";
        assertEquals(expected, WordUtils.wrap(input, 10, null, true));
    }

    @Test
    public void testWrapMinimumWrapLength() {
        String input = "word wrap test";
        String expected = "w\nord\nwr\nap\nt\ne\nst";
        assertEquals(expected, WordUtils.wrap(input, 1, "\n", true));
    }

    @Test
    public void testWrapNegativeWrapLength() {
        String input = "This is a test";
        String expected = "T\nh\ni\ns\n\n\ni\ns\n\n\na\n\n\nt\ne\ns\nt";
        assertEquals(expected, WordUtils.wrap(input, -5, "\n", true));
    }

    @Test
    public void testWrapWithSpaces() {
        String input = "word   wrap test";
        String expected = "word\nwrap\ntest";
        assertEquals(expected, WordUtils.wrap(input, 5, "\n", true));
    }
}
