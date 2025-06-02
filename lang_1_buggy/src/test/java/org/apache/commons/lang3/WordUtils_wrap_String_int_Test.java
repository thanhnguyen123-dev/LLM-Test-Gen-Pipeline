package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
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
    public void testShortStringNoWrapNeeded() {
        assertEquals("Hello", WordUtils.wrap("Hello", 10));
    }

    @Test
    public void testLongWordNoWrap() {
        assertEquals("unwrappableword", WordUtils.wrap("unwrappableword", 5));
    }

    @Test
    public void testWrapAtLength() {
        assertEquals("Hello\nWorld", WordUtils.wrap("Hello World", 5));
    }

    @Test
    public void testWrapMultipleLines() {
        assertEquals("Hello\nWorld\nThis\nis a\nTest", WordUtils.wrap("Hello World This is a Test", 5));
    }

    @Test
    public void testWrapWithLeadingSpaces() {
        assertEquals("Hello\n World", WordUtils.wrap("Hello World", 6));
    }

    @Test
    public void testWrapWithSpecialCharacters() {
        assertEquals("Hello-\nWorld!", WordUtils.wrap("Hello-World!", 6));
    }

    @Test
    public void testWrapWithWrapLengthLessThanOne() {
        assertEquals("H\ne\nl\nl\no", WordUtils.wrap("Hello", 0));
    }

    @Test
    public void testWrapWithBoundaryValue() {
        assertEquals("Hello\nWorld\nat\nBoundary", WordUtils.wrap("Hello World at Boundary", Integer.MAX_VALUE));
    }
}
