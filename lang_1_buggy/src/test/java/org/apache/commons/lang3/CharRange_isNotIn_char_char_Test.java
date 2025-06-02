package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharRange_isNotIn_char_char_Test {

    @Test
    public void testTypicalRange() {
        CharRange range = CharRange.isNotIn('a', 'z');
        assertFalse(range.contains('b'));
        assertTrue(range.contains('A'));
    }

    @Test
    public void testSingleElementRange() {
        CharRange range = CharRange.isNotIn('a', 'a');
        assertFalse(range.contains('a'));
        assertTrue(range.contains('b'));
    }

    @Test
    public void testReverseRange() {
        try {
            CharRange.isNotIn('z', 'a');
            fail("Expected IllegalArgumentException for reverse range");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testEdgeCases() {
        CharRange range = CharRange.isNotIn(Character.MIN_VALUE, Character.MAX_VALUE);
        assertFalse(range.contains('a'));
        assertFalse(range.contains('z'));
    }

    @Test
    public void testEmptyRange() {
        CharRange range = CharRange.isNotIn((char) 0, (char) 0);
        assertFalse(range.contains((char) 0));
        assertTrue(range.contains((char) 1));
    }
}
