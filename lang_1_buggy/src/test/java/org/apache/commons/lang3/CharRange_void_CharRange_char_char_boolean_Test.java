package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_void_CharRange_char_char_boolean_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Using a public static method for setup since the constructor is private
        charRange = CharRange.of('a', 'e', false);
    }

    @Test
    public void testTypicalRange() {
        CharRange range = CharRange.of('a', 'e', false);
        assertFalse(range.isNegated());
        assertEquals('a', range.getStart());
        assertEquals('e', range.getEnd());
    }

    @Test
    public void testNegatedRange() {
        CharRange range = CharRange.of('a', 'e', true);
        assertTrue(range.isNegated());
        assertEquals('a', range.getStart());
        assertEquals('e', range.getEnd());
    }

    @Test
    public void testReversedRange() {
        CharRange range = CharRange.of('e', 'a', false);
        assertFalse(range.isNegated());
        assertEquals('a', range.getStart());
        assertEquals('e', range.getEnd());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRange() {
        CharRange.of('a', 'a', false);
    }

    @Test
    public void testSingleCharRange() {
        CharRange range = CharRange.of('a', 'a', false);
        assertFalse(range.isNegated());
        assertEquals('a', range.getStart());
        assertEquals('a', range.getEnd());
    }
}
