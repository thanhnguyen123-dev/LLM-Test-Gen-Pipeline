package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class WordUtils_capitalizeFully_String_Test {

    @Test
    public void testCapitalizeFully_NullInput() {
        String result = WordUtils.capitalizeFully(null);
        assertNull(result);
    }

    @Test
    public void testCapitalizeFully_EmptyString() {
        String result = WordUtils.capitalizeFully("");
        assertEquals("", result);
    }

    @Test
    public void testCapitalizeFully_SingleWordLowerCase() {
        String result = WordUtils.capitalizeFully("hello");
        assertEquals("Hello", result);
    }

    @Test
    public void testCapitalizeFully_SingleWordUpperCase() {
        String result = WordUtils.capitalizeFully("WORLD");
        assertEquals("World", result);
    }

    @Test
    public void testCapitalizeFully_MixedCase() {
        String result = WordUtils.capitalizeFully("i am FINE");
        assertEquals("I Am Fine", result);
    }

    @Test
    public void testCapitalizeFully_SpacesBetweenWords() {
        String result = WordUtils.capitalizeFully(" let's try that again ");
        assertEquals(" Let's Try That Again ", result);
    }

    @Test
    public void testCapitalizeFully_TabAndNewline() {
        String result = WordUtils.capitalizeFully("hello\tworld\nnew line");
        assertEquals("Hello\tWorld\nNew Line", result);
    }

    @Test
    public void testCapitalizeFully_NumericCharacter() {
        String result = WordUtils.capitalizeFully("123 abc");
        assertEquals("123 Abc", result);
    }
}
