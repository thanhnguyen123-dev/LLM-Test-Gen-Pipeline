package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordUtils_swapCase_String_Test {

    @Test
    public void testSwapCase_NullInput() {
        assertNull(WordUtils.swapCase(null));
    }

    @Test
    public void testSwapCase_EmptyString() {
        assertEquals("", WordUtils.swapCase(""));
    }

    @Test
    public void testSwapCase_MixedCase() {
        assertEquals("tHE DOG HAS A bone", WordUtils.swapCase("The dog has a BONE"));
    }

    @Test
    public void testSwapCase_UpperCase() {
        assertEquals("hello WORLD", WordUtils.swapCase("HELLO world"));
    }

    @Test
    public void testSwapCase_LowerCase() {
        assertEquals("HELLO wORLD", WordUtils.swapCase("hello WORLD"));
    }

    @Test
    public void testSwapCase_TitleCaseMiddle() {
        assertEquals("tHis Is a TEST", WordUtils.swapCase("This iS A test"));
    }

    @Test
    public void testSwapCase_WhitespaceHandling() {
        assertEquals("tITLE cASE tEST", WordUtils.swapCase("title CASE Test"));
    }
}
