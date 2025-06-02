package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_swapCase_String_Test {

    @Before
    public void setUp() {
        // No setup required for static method
    }

    @Test
    public void testSwapCase_NullInput() {
        assertNull(WordUtils.swapCase(null));
    }

    @Test
    public void testSwapCase_EmptyString() {
        assertEquals("", WordUtils.swapCase(""));
    }

    @Test
    public void testSwapCase_TypicalUseCase() {
        assertEquals("tHE DOG HAS A bone", WordUtils.swapCase("The dog has a BONE"));
    }

    @Test
    public void testSwapCase_AllUpperCase() {
        assertEquals("all upper", WordUtils.swapCase("ALL UPPER"));
    }

    @Test
    public void testSwapCase_AllLowerCase() {
        assertEquals("ALL LOWER", WordUtils.swapCase("all lower"));
    }

    @Test
    public void testSwapCase_TitleCase() {
        assertEquals("tHis IS a Title", WordUtils.swapCase("This is A tITLE"));
    }

    @Test
    public void testSwapCase_SingleCharacter() {
        assertEquals("A", WordUtils.swapCase("a"));
        assertEquals("a", WordUtils.swapCase("A"));
    }

    @Test
    public void testSwapCase_SpecialCharacters() {
        assertEquals("123 !@#", WordUtils.swapCase("123 !@#"));
        assertEquals("hElLo WorlD!", WordUtils.swapCase("HeLlO wORLd!"));
    }

    @Test
    public void testSwapCase_WhitespaceHandling() {
        assertEquals("hELLO\nWORLD", WordUtils.swapCase("Hello\nWorld"));
        assertEquals("hELLO\tWORLD", WordUtils.swapCase("Hello\tWorld"));
    }
}
