package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_wrap_String_int_String_boolean_Test {

    @Test
    public void testWrap_NullString() {
        String result = WordUtils.wrap(null, 10, "\n", false);
        assertNull(result);
    }

    @Test
    public void testWrap_EmptyString() {
        String result = WordUtils.wrap("", 10, "\n", false);
        assertEquals("", result);
    }

    @Test
    public void testWrap_SingleWord_NotWrapped() {
        String result = WordUtils.wrap("hello", 10, "\n", false);
        assertEquals("hello", result);
    }

    @Test
    public void testWrap_SingleWord_WrappedLongWords() {
        String result = WordUtils.wrap("hello", 3, "\n", true);
        assertEquals("hel\nlo", result);
    }

    @Test
    public void testWrap_MultipleWords_NormalWrap() {
        String result = WordUtils.wrap("hello world", 5, "\n", false);
        assertEquals("hello\nworld", result);
    }

    @Test
    public void testWrap_NoSpace_WrapLongWords() {
        String result = WordUtils.wrap("helloworld", 5, "\n", true);
        assertEquals("hello\nworld", result);
    }

    @Test
    public void testWrap_MultipleSpaces() {
        String result = WordUtils.wrap("hello   world", 5, "\n", false);
        assertEquals("hello\nworld", result);
    }

    @Test
    public void testWrap_VaryingLineLength() {
        String result = WordUtils.wrap("a long line of text gets wrapped", 7, "\n", false);
        assertEquals("a long\nline of\ntext\ngets\nwrapped", result);
    }

    @Test
    public void testWrap_CustomNewLine() {
        String result = WordUtils.wrap("hello world", 5, "<br>", false);
        assertEquals("hello<br>world", result);
    }

    @Test
    public void testWrap_WrapLengthLessThanOne() {
        String result = WordUtils.wrap("hello world", 0, "\n", false);
        assertEquals("h\ne\nl\nl\no\n \nw\no\nr\nl\nd", result);
    }

    @Test
    public void testWrap_WrapLongWordsFalse() {
        String result = WordUtils.wrap("helloworld", 5, "\n", false);
        assertEquals("helloworld", result);
    }
}
