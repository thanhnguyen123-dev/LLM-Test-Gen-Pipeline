package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_String_uncapitalize_String_charArray_Test {

    private WordUtils wordUtils;

    @Before
    public void setUp() {
        wordUtils = new WordUtils();
    }

    @Test
    public void testUncapitalize_NullString() {
        assertNull(WordUtils.uncapitalize(null, new char[] {' '}));
    }

    @Test
    public void testUncapitalize_EmptyString() {
        assertEquals("", WordUtils.uncapitalize("", new char[] {' '}));
    }

    @Test
    public void testUncapitalize_NoDelimiters() {
        assertEquals("unchanged", WordUtils.uncapitalize("unchanged", null));
    }

    @Test
    public void testUncapitalize_EmptyDelimiters() {
        assertEquals("unchanged", WordUtils.uncapitalize("unchanged", new char[0]));
    }

    @Test
    public void testUncapitalize_SingleDelimiter() {
        assertEquals("i AM.fINE", WordUtils.uncapitalize("I AM.FINE", new char[] {'.'}));
    }

    @Test
    public void testUncapitalize_DefaultWhitespaceDelimiters() {
        assertEquals("i aM fINE", WordUtils.uncapitalize("I AM FINE", null));
    }

    @Test
    public void testUncapitalize_MixedDelimiters() {
        assertEquals("hello.World!this Is.MIXED", WordUtils.uncapitalize("Hello.World!This Is.MIXED", new char[] {'.', '!'}));
    }

    @Test
    public void testUncapitalize_NoEffectOnNonFirstCharacters() {
        assertEquals("HeLLo WoRLD", WordUtils.uncapitalize("HeLLo WoRLD", new char[] {' '}));
    }

    @Test
    public void testUncapitalize_AllDelimiters() {
        assertEquals("this!is@a#test", WordUtils.uncapitalize("This!Is@A#Test", new char[] {'!', '@', '#'}));
    }
}
