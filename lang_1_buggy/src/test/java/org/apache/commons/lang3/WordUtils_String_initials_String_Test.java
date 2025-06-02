package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_String_initials_String_Test {

    @Test
    public void testInitials_NullInput() {
        assertNull(WordUtils.initials(null));
    }

    @Test
    public void testInitials_EmptyString() {
        assertEquals("", WordUtils.initials(""));
    }

    @Test
    public void testInitials_SingleWord() {
        assertEquals("B", WordUtils.initials("Ben"));
    }

    @Test
    public void testInitials_MultipleWords() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee"));
    }

    @Test
    public void testInitials_MixedSeparators() {
        assertEquals("BJ", WordUtils.initials("Ben J.Lee"));
    }

    @Test
    public void testInitials_LeadingWhitespace() {
        assertEquals("BJL", WordUtils.initials(" Ben John Lee"));
    }

    @Test
    public void testInitials_TrailingWhitespace() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee "));
    }

    @Test
    public void testInitials_WhitespaceOnly() {
        assertEquals("", WordUtils.initials("   "));
    }

    @Test
    public void testInitials_NonAlphabeticCharacters() {
        assertEquals("123", WordUtils.initials("123 456 789"));
    }
}
