package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordUtils_String_initials_String_charArray_Test {

    @Test
    public void testInitials_NullString() {
        assertNull(WordUtils.initials(null, null));
    }

    @Test
    public void testInitials_EmptyString() {
        assertEquals("", WordUtils.initials("", null));
    }

    @Test
    public void testInitials_NoDelimiters_NullArray() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee", null));
    }

    @Test
    public void testInitials_NoDelimiters_HasPeriods() {
        assertEquals("BJ", WordUtils.initials("Ben J.Lee", null));
    }

    @Test
    public void testInitials_WithDelimiters() {
        assertEquals("BJL", WordUtils.initials("Ben J.Lee", new char[] {' ', '.'}));
    }

    @Test
    public void testInitials_EmptyDelimiters() {
        assertEquals("", WordUtils.initials("Whatever", new char[0]));
    }

    @Test
    public void testInitials_SingleCharacterString() {
        assertEquals("A", WordUtils.initials("A", null));
    }

    @Test
    public void testInitials_DelimitersOnly() {
        assertEquals("", WordUtils.initials(" . ", new char[] {' ', '.'}));
    }

    @Test
    public void testInitials_ComplexString() {
        assertEquals("TQ", WordUtils.initials("The Quick", new char[] {' '}));
    }

    @Test
    public void testInitials_BoundaryTest() {
        assertEquals("A", WordUtils.initials(" A", new char[] {' '}));
    }

    // Add more edge case tests as necessary
}
