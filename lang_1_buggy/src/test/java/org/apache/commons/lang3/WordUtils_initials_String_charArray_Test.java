package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.apache.commons.lang3.text.WordUtils;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_initials_String_charArray_Test {

    @Test
    public void testInitials_NullString() {
        assertNull(WordUtils.initials(null, new char[]{'.', ' '}));
    }

    @Test
    public void testInitials_EmptyString() {
        assertEquals("", WordUtils.initials("", new char[]{'.', ' '}));
    }

    @Test
    public void testInitials_NullDelimiters() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee", null));
    }

    @Test
    public void testInitials_EmptyDelimiters() {
        assertEquals("", WordUtils.initials("Ben John Lee", new char[0]));
    }

    @Test
    public void testInitials_SingleDelimiter() {
        assertEquals("BJ", WordUtils.initials("Ben J.Lee", null));
    }

    @Test
    public void testInitials_MultipleDelimiters() {
        assertEquals("BJL", WordUtils.initials("Ben J.Lee", new char[]{'.', ' '}));
    }

    @Test
    public void testInitials_TypicalUseCase() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee", new char[]{'.', ' ', '-'}));
    }

    @Test
    public void testInitials_SingleCharacter() {
        assertEquals("A", WordUtils.initials("A", new char[]{'.', ' '}));
    }

    @Test
    public void testInitials_MixedCase() {
        assertEquals("USB", WordUtils.initials("Universal Serial Bus", new char[]{' '}));
    }

    @Test
    public void testInitials_WhitespaceAsDelimiter() {
        assertEquals("JDK", WordUtils.initials("Java Development Kit", null));
    }

    @Test
    public void testInitials_SpecialCharacters() {
        assertEquals("BJLH", WordUtils.initials("Ben-John_Lee_Hunter", new char[]{'-', '_'}));
    }

    @Test
    public void testInitials_OnlyDelimiters() {
        assertEquals("", WordUtils.initials("---", new char[]{'-'}));
    }

    @Test
    public void testInitials_LeadingAndTrailingSpaces() {
        assertEquals("AT", WordUtils.initials("  Albert Tom   ", null));
    }
}
