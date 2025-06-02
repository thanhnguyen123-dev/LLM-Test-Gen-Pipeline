package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CharRange_int_hashCode_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Typical CharRange instance
        charRange = CharRange.is('a');
    }

    @Test
    public void testTypicalCase() {
        CharRange range = CharRange.is('a');
        int expectedHashCode = 83 + 'a' + 7 * 'a' + 0;
        assertEquals(expectedHashCode, range.hashCode());
    }

    @Test
    public void testNegatedCase() {
        CharRange range = CharRange.isNot('a');
        int expectedHashCode = 83 + 'a' + 7 * 'a' + 1;
        assertEquals(expectedHashCode, range.hashCode());
    }

    @Test
    public void testSingleElementEdgeCase() {
        CharRange range = CharRange.is('\0');
        int expectedHashCode = 83 + '\0' + 0 + 7 * '\0';
        assertEquals(expectedHashCode, range.hashCode());
    }

    @Test
    public void testEmptyCase() {
        CharRange range = CharRange.isIn('\0', '\0');
        int expectedHashCode = 83 + '\0' + 7 * '\0' + 0;
        assertEquals(expectedHashCode, range.hashCode());
    }

    @Test
    public void testBoundaryCaseEnd() {
        CharRange range = CharRange.isIn('a', 'z');
        int expectedHashCode = 83 + 'a' + 7 * 'z' + 0;
        assertEquals(expectedHashCode, range.hashCode());
    }

    @Test
    public void testBoundaryCaseStart() {
        CharRange range = CharRange.is('z');
        int expectedHashCode = 83 + 'z' + 7 * 'z' + 0;
        assertEquals(expectedHashCode, range.hashCode());
    }
}
