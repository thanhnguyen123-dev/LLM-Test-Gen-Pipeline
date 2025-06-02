package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WordUtils_initials_String_Test {

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
    public void testInitials_WithPunctuation() {
        assertEquals("BJ", WordUtils.initials("Ben J.Lee"));
    }

    @Test
    public void testInitials_WhitespaceVariation() {
        assertEquals("BJL", WordUtils.initials(" Ben   John\tLee"));
    }

    @Test
    public void testInitials_LeadingWhitespace() {
        assertEquals("BJL", WordUtils.initials(" Ben John Lee"));
    }

    @Test
    public void testInitials_TrailingWhitespace() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee "));
    }
}
