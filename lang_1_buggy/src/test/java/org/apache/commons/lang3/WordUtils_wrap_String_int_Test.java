package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.text.WordUtils;

public class WordUtils_wrap_String_int_Test {

    @Test
    public void testWrapNullString() {
        assertNull(WordUtils.wrap(null, 10));
    }

    @Test
    public void testWrapEmptyString() {
        assertEquals("", WordUtils.wrap("", 10));
    }

    @Test
    public void testWrapStringWithWrapLengthZero() {
        assertEquals("a\nb\nc", WordUtils.wrap("a b c", 0));
    }

    @Test
    public void testWrapStringWithWrapLengthNegative() {
        assertEquals("a\nb\nc", WordUtils.wrap("a b c", -1));
    }

    @Test
    public void testWrapStringWithWrapLengthLessThanWordLength() {
        assertEquals("abcdefg\nhij", WordUtils.wrap("abcdefghij", 7));
    }

    @Test
    public void testWrapStringWithMultipleSpaces() {
        assertEquals("a\nb", WordUtils.wrap("a       b", 1));
    }

    @Test
    public void testWrapStringWithLargeWrapLength() {
        assertEquals("abcdefghijklmnopqrstuvwxyz", WordUtils.wrap("abcdefghijklmnopqrstuvwxyz", 50));
    }

    @Test
    public void testWrapStringWithExactWrapLength() {
        assertEquals("abc\ndef", WordUtils.wrap("abcdef", 3));
    }

    @Test
    public void testWrapStringWithSystemNewLine() {
        String input = "a b c";
        String expected = "a\nb\nc";
        assertEquals(expected, WordUtils.wrap(input, 1));
    }
}
