package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordUtils_capitalizeFully_String_Test {

    @Test
    public void testCapitalizeFully_NullInput() {
        assertEquals(null, WordUtils.capitalizeFully(null));
    }

    @Test
    public void testCapitalizeFully_EmptyString() {
        assertEquals("", WordUtils.capitalizeFully(""));
    }

    @Test
    public void testCapitalizeFully_SingleWord() {
        assertEquals("Fine", WordUtils.capitalizeFully("fINE"));
    }

    @Test
    public void testCapitalizeFully_MultipleWords() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("i am FINE"));
    }

    @Test
    public void testCapitalizeFully_AlreadyCapitalized() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("I Am Fine"));
    }

    @Test
    public void testCapitalizeFully_AllLowerCase() {
        assertEquals("This Is A Test", WordUtils.capitalizeFully("this is a test"));
    }

    @Test
    public void testCapitalizeFully_MixedCase() {
        assertEquals("Title Case String", WordUtils.capitalizeFully("tItle cAse StrIng"));
    }

    @Test
    public void testCapitalizeFully_WithPunctuation() {
        assertEquals("Hello, World!", WordUtils.capitalizeFully("hello, World!"));
    }
}
