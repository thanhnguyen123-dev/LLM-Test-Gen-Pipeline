package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_wrap_String_int_Test {

    @Test
    public void testWrap_NullString() {
        assertNull(WordUtils.wrap(null, 10));
    }

    @Test
    public void testWrap_EmptyString() {
        assertEquals("", WordUtils.wrap("", 10));
    }

    @Test
    public void testWrap_SingleWord() {
        String str = "unbreakable";
        assertEquals(str, WordUtils.wrap(str, 10));
    }

    @Test
    public void testWrap_MultipleWords() {
        String str = "word wrap test case";
        String expected = "word wrap\ntest case";
        assertEquals(expected, WordUtils.wrap(str, 10));
    }

    @Test
    public void testWrap_LongWord() {
        String str = "thisisaverylongwordthatexceedsthewraplength";
        assertEquals(str, WordUtils.wrap(str, 10));
    }

    @Test
    public void testWrap_WrapLengthLessThanOne() {
        String str = "small word";
        String expected = "small\nword";
        assertEquals(expected, WordUtils.wrap(str, 0));
    }

    @Test
    public void testWrap_WrapAtExactLength() {
        String str = "12345 67890";
        String expected = "12345\n67890";
        assertEquals(expected, WordUtils.wrap(str, 5));
    }
}
