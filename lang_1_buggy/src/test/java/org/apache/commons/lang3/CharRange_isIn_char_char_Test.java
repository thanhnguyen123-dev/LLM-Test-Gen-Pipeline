package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_isIn_char_char_Test {

    @Test
    public void testTypicalRange() {
        CharRange range = CharRange.isIn('a', 'z');
        assertTrue(range.contains('a'));
        assertTrue(range.contains('m'));
        assertTrue(range.contains('z'));
        assertFalse(range.contains('A'));
        assertFalse(range.contains('Z'));
    }

    @Test
    public void testSingleCharacterRange() {
        CharRange range = CharRange.isIn('a', 'a');
        assertTrue(range.contains('a'));
        assertFalse(range.contains('b'));
    }

    @Test
    public void testReversedRange() {
        CharRange range = CharRange.isIn('z', 'a');
        assertFalse(range.contains('a'));
        assertFalse(range.contains('z'));
    }

    @Test
    public void testMaxValueEdgeCase() {
        CharRange range = CharRange.isIn(Character.MAX_VALUE, Character.MAX_VALUE);
        assertTrue(range.contains(Character.MAX_VALUE));
        assertFalse(range.contains(Character.MAX_VALUE - 1));
    }

    @Test
    public void testMinValueEdgeCase() {
        CharRange range = CharRange.isIn(Character.MIN_VALUE, Character.MIN_VALUE);
        assertTrue(range.contains(Character.MIN_VALUE));
        assertFalse(range.contains(Character.MIN_VALUE + 1));
    }

    @Test(expected = NullPointerException.class)
    public void testNullInputHandling() {
        CharRange range = CharRange.isIn('\0', '\0'); // Assuming '\0' input may cause null in internal logic
    }
}
