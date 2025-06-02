package org.apache.commons.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;

public class WordUtils_wrap_String_int_String_boolean_Test {

    @Test
    public void testWrap_NullInput() {
        String result = WordUtils.wrap(null, 10, "\n", true);
        assertNull(result);
    }

    @Test
    public void testWrap_EmptyString() {
        String result = WordUtils.wrap("", 10, "\n", true);
        assertEquals("", result);
    }

    @Test
    public void testWrap_LineWrapping() {
        String result = WordUtils.wrap("The quick brown fox jumps over the lazy dog", 10, "\n", true);
        assertEquals("The quick\nbrown fox\njumps over\nthe lazy\ndog", result);
    }

    @Test
    public void testWrap_LongWordsWrappingEnabled() {
        String result = WordUtils.wrap("Jumping", 3, "\n", true);
        assertEquals("Jum\npin\ng", result);
    }

    @Test
    public void testWrap_LongWordsWrappingDisabled() {
        String result = WordUtils.wrap("Jumping", 3, "\n", false);
        assertEquals("Jumping", result);
    }

    @Test
    public void testWrap_NewLineStrNull() {
        String result = WordUtils.wrap("Word wrap test", 4, null, true);
        assertEquals("Word\nwrap\ntest", result);
    }

    @Test
    public void testWrap_WrapLengthLessThanOne() {
        String result = WordUtils.wrap("short test", 0, "\n", true);
        assertEquals("s\nh\no\nr\nt\nt\ne\ns\nt", result);
    }

    @Test
    public void testWrap_Whitespace() {
        String result = WordUtils.wrap("     ", 2, "\n", false);
        assertEquals("", result);
    }

    @Test
    public void testWrap_LeadingSpaces() {
        String result = WordUtils.wrap("   leading space", 5, "\n", true);
        assertEquals("lea\nding\nspace", result);
    }

    @Test
    public void testWrap_TrailingSpaces() {
        String result = WordUtils.wrap("trailing space   ", 8, "\n", true);
        assertEquals("trailing\nspace", result);
    }
}
