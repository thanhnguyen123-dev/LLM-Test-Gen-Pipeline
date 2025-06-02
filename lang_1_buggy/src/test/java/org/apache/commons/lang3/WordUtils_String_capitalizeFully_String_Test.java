package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_String_capitalizeFully_String_Test {

    @Test
    public void testCapitalizeFully_NullInput() {
        assertNull(WordUtils.capitalizeFully(null));
    }

    @Test
    public void testCapitalizeFully_EmptyString() {
        assertEquals("", WordUtils.capitalizeFully(""));
    }

    @Test
    public void testCapitalizeFully_SingleWord() {
        assertEquals("Fine", WordUtils.capitalizeFully("fine"));
    }

    @Test
    public void testCapitalizeFully_MultipleWords() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("i am FINE"));
    }

    @Test
    public void testCapitalizeFully_SingleLetterWords() {
        assertEquals("A B C", WordUtils.capitalizeFully("a b c"));
    }

    @Test
    public void testCapitalizeFully_WithSpaces() {
        assertEquals("  I Am Fine  ", WordUtils.capitalizeFully("  i am FINE  "));
    }

    @Test
    public void testCapitalizeFully_AlreadyCapitalized() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("I Am Fine"));
    }

    @Test
    public void testCapitalizeFully_WordWithNumbers() {
        assertEquals("Test 123", WordUtils.capitalizeFully("test 123"));
    }

    @Test
    public void testCapitalizeFully_NonAlphabeticCharacters() {
        assertEquals("Hello-World", WordUtils.capitalizeFully("hello-world"));
    }
}
