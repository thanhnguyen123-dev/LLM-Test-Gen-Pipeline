package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_swapCase_String_Test {

    @Test
    public void testSwapCase_withNullInput() {
        assertNull(WordUtils.swapCase(null));
    }

    @Test
    public void testSwapCase_withEmptyString() {
        assertEquals("", WordUtils.swapCase(""));
    }

    @Test
    public void testSwapCase_withUppercase() {
        assertEquals("hello", WordUtils.swapCase("HELLO"));
    }

    @Test
    public void testSwapCase_withLowercase() {
        assertEquals("HELLO", WordUtils.swapCase("hello"));
    }

    @Test
    public void testSwapCase_withMixedCase() {
        assertEquals("tHE DOG HAS A bone", WordUtils.swapCase("The dog has a BONE"));
    }

    @Test
    public void testSwapCase_withTitleCaseCharacter() {
        assertEquals("tHis Is A tEst", WordUtils.swapCase("This is a Test"));
    }

    @Test
    public void testSwapCase_withWhitespaceAndLowercase() {
        assertEquals("Hello World", WordUtils.swapCase("hELLO wORLD"));
    }

    @Test
    public void testSwapCase_withNoWhitespace() {
        assertEquals("jAVA pROGRAM", WordUtils.swapCase("Java Program"));
    }

    @Test
    public void testSwapCase_withSpecialCharacters() {
        assertEquals("123 ABC!#", WordUtils.swapCase("123 abc!#"));
    }
}
