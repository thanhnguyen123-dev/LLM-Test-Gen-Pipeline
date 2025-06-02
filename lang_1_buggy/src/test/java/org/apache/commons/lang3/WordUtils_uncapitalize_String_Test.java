package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordUtils_uncapitalize_String_Test {

    @Test
    public void testUncapitalize_NullInput() {
        assertNull(WordUtils.uncapitalize(null));
    }

    @Test
    public void testUncapitalize_EmptyString() {
        assertEquals("", WordUtils.uncapitalize(""));
    }

    @Test
    public void testUncapitalize_SingleWord() {
        assertEquals("word", WordUtils.uncapitalize("Word"));
    }

    @Test
    public void testUncapitalize_MultiWord() {
        assertEquals("hello world", WordUtils.uncapitalize("Hello World"));
    }

    @Test
    public void testUncapitalize_MixedCase() {
        assertEquals("i am fINE", WordUtils.uncapitalize("I Am FINE"));
    }

    @Test
    public void testUncapitalize_SingleLetterWords() {
        assertEquals("a b c", WordUtils.uncapitalize("A B C"));
    }

    @Test
    public void testUncapitalize_MixedWhitespaces() {
        assertEquals(" hi there ", WordUtils.uncapitalize(" Hi There "));
    }

    @Test
    public void testUncapitalize_NonWhitespaceDelimiter() {
        assertEquals("word-word", WordUtils.uncapitalize("Word-Word"));
    }
}
