package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordUtils_uncapitalize_String_charArray_Test {

    @Test
    public void testUncapitalize_NullInput() {
        assertNull(WordUtils.uncapitalize(null, null));
    }

    @Test
    public void testUncapitalize_EmptyString() {
        assertEquals("", WordUtils.uncapitalize("", null));
    }

    @Test
    public void testUncapitalize_NullDelimiters() {
        assertEquals("i am fine", WordUtils.uncapitalize("I Am Fine", null));
    }

    @Test
    public void testUncapitalize_EmptyDelimiters() {
        assertEquals("i am fine", WordUtils.uncapitalize("I Am Fine", new char[0]));
    }

    @Test
    public void testUncapitalize_SpecificDelimiters() {
        assertEquals("i am.fine", WordUtils.uncapitalize("I AM.FINE", new char[]{'.'}));
    }

    @Test
    public void testUncapitalize_NoDelimitersInString() {
        assertEquals("i am fine", WordUtils.uncapitalize("I Am Fine", new char[]{','}));
    }

    @Test
    public void testUncapitalize_SingleCharacterString() {
        assertEquals("a", WordUtils.uncapitalize("A", null));
    }

    @Test
    public void testUncapitalize_NonAlphaFirstCharacter() {
        assertEquals("!hello world", WordUtils.uncapitalize("!Hello World", null));
    }

    @Test
    public void testUncapitalize_FirstCharDelimiter() {
        assertEquals(".hello", WordUtils.uncapitalize(".Hello", new char[]{'.'}));
    }
}
