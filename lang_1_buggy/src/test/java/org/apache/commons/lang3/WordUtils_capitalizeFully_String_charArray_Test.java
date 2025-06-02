package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordUtils_capitalizeFully_String_charArray_Test {

    @Test
    public void testCapitalizeFully_NullString() {
        assertNull(WordUtils.capitalizeFully(null, new char[]{'.'}));
    }

    @Test
    public void testCapitalizeFully_EmptyString() {
        assertEquals("", WordUtils.capitalizeFully("", new char[]{'.'}));
    }

    @Test
    public void testCapitalizeFully_NullDelimiters() {
        assertEquals("Hello World", WordUtils.capitalizeFully("hello world", null));
    }

    @Test
    public void testCapitalizeFully_EmptyDelimiters() {
        assertEquals("Hello World", WordUtils.capitalizeFully("hello world", new char[0]));
    }

    @Test
    public void testCapitalizeFully_MixedCase() {
        assertEquals("I Am.Fine", WordUtils.capitalizeFully("i aM.fine", new char[]{'.'}));
    }

    @Test
    public void testCapitalizeFully_AllCapsString() {
        assertEquals("Hello World", WordUtils.capitalizeFully("HELLO WORLD", new char[]{' '}));
    }

    @Test
    public void testCapitalizeFully_NoDelimitersString() {
        assertEquals("Hello World", WordUtils.capitalizeFully("hello world", new char[]{' '}));
    }

    @Test
    public void testCapitalizeFully_SingleCharacterDelimiters() {
        assertEquals("Hello-World", WordUtils.capitalizeFully("hello-world", new char[]{'-'}));
    }

    @Test
    public void testCapitalizeFully_DelimitersInMiddle() {
        assertEquals("Hello-World Ola", WordUtils.capitalizeFully("hello-world ola", new char[]{' ', '-'}));
    }
}
