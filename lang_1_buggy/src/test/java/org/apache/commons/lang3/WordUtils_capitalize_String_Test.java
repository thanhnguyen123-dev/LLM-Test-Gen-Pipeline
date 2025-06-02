package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.text.WordUtils;

public class WordUtils_capitalize_String_Test {

    @Test
    public void testCapitalizeNullInput() {
        assertNull(WordUtils.capitalize(null));
    }

    @Test
    public void testCapitalizeEmptyString() {
        assertEquals("", WordUtils.capitalize(""));
    }

    @Test
    public void testCapitalizeSingleWordLowerCase() {
        assertEquals("Hello", WordUtils.capitalize("hello"));
    }

    @Test
    public void testCapitalizeMultipleWordsWithSpaces() {
        assertEquals("Hello World", WordUtils.capitalize("hello world"));
    }

    @Test
    public void testCapitalizeMultipleWordsWithMixedCase() {
        assertEquals("I Am Fine", WordUtils.capitalize("i am FINE"));
    }

    @Test
    public void testCapitalizeMultipleSpaces() {
        assertEquals("Hello  World", WordUtils.capitalize("hello  world"));
    }

    @Test
    public void testCapitalizeWithLeadingSpaces() {
        assertEquals("   Hello", WordUtils.capitalize("   hello"));
    }

    @Test
    public void testCapitalizeWithTrailingSpaces() {
        assertEquals("Hello   ", WordUtils.capitalize("hello   "));
    }
}
