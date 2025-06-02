package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class WordUtils_isDelimiter_char_charArray_Test {

    @Test
    public void testIsDelimiterWithNullDelimiters() {
        assertTrue("Expected whitespace character to be considered as default delimiter.",
                WordUtils.isDelimiter(' ', null));
        assertFalse("Expected non-whitespace character to not be considered a delimiter.",
                WordUtils.isDelimiter('a', null));
    }

    @Test
    public void testIsDelimiterWithEmptyDelimiters() {
        assertFalse("Expected character to not be considered a delimiter when delimiters array is empty.",
                WordUtils.isDelimiter('a', new char[]{}));
    }

    @Test
    public void testIsDelimiterWithSingleCharInDelimiters() {
        assertTrue("Expected character to match single-element delimiter array.",
                WordUtils.isDelimiter('a', new char[]{'a'}));
        assertFalse("Expected character to not match non-existing delimiter in single-element array.",
                WordUtils.isDelimiter('b', new char[]{'a'}));
    }

    @Test
    public void testIsDelimiterWithMultipleCharsInDelimiters() {
        assertTrue("Expected character to match one of the delimiters.",
                WordUtils.isDelimiter('a', new char[]{'a', 'b', 'c'}));
        assertTrue("Expected character to match one of the delimiters.",
                WordUtils.isDelimiter('b', new char[]{'a', 'b', 'c'}));
        assertTrue("Expected character to match one of the delimiters.",
                WordUtils.isDelimiter('c', new char[]{'a', 'b', 'c'}));
        assertFalse("Expected character to not match any of the delimiters.",
                WordUtils.isDelimiter('d', new char[]{'a', 'b', 'c'}));
    }

    @Test
    public void testIsDelimiterWithNonWhitespaceDefault() {
        assertFalse("Expected non-whitespace character to not be considered a delimiter.",
                WordUtils.isDelimiter('x', null));
    }
}
