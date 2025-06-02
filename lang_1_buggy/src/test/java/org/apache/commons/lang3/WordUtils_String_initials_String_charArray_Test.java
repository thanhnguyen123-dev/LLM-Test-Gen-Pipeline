package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_String_initials_String_charArray_Test {

    @Test
    public void testNullString() {
        assertNull(WordUtils.initials(null, new char[]{' '}));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", WordUtils.initials("", new char[]{' '}));
    }

    @Test
    public void testNullDelimiters() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee", null));
    }

    @Test
    public void testEmptyDelimiters() {
        assertEquals("", WordUtils.initials("Ben John Lee", new char[0]));
    }

    @Test
    public void testWhitespaceDelimiters() {
        assertEquals("BJ", WordUtils.initials("Ben J.Lee", null));
    }

    @Test
    public void testDotAndSpaceDelimiters() {
        assertEquals("BJL", WordUtils.initials("Ben J.Lee", new char[]{' ', '.'}));
    }

    @Test
    public void testSingleCharacterString() {
        assertEquals("B", WordUtils.initials("B", new char[]{' '}));
    }

    @Test
    public void testStringWithoutDelimiters() {
        assertEquals("B", WordUtils.initials("Ben", new char[]{' '}));
    }

    @Test
    public void testStringWithMultipleConsecutiveDelimiters() {
        assertEquals("BJL", WordUtils.initials("Ben  J.  Lee", new char[]{' ', '.'}));
    }

    @Test
    public void testNonAlphaCharacters() {
        assertEquals("B1L", WordUtils.initials("Ben1 J@. Lee#2", new char[]{' ', '.', '@', '#', '1', '2'}));
    }

    @Test
    public void testStringWithNonAlphabetsOnly() {
        assertEquals("", WordUtils.initials("!!!", new char[]{'!'}));
    }
}
