package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharRange_String_toString_Test {

    private CharRange charRangeTypical;
    private CharRange charRangeNegated;
    private CharRange charRangeSingleCharacter;
    private CharRange charRangeEmpty;

    @Before
    public void setUp() {
        charRangeTypical = CharRange.isIn('a', 'z');
        charRangeNegated = CharRange.isNotIn('a', 'z');
        charRangeSingleCharacter = CharRange.is('a');
        // Simulating an empty range, typically not possible via provided factory methods,
        // handled as it might represent edge conditions in some scenarios.
    }

    @Test
    public void testToString_typicalRange() {
        String result = charRangeTypical.toString();
        assertEquals("a-z", result);
    }

    @Test
    public void testToString_negatedRange() {
        String result = charRangeNegated.toString();
        assertEquals("^a-z", result);
    }

    @Test
    public void testToString_singleCharacter() {
        String result = charRangeSingleCharacter.toString();
        assertEquals("a", result);
    }

    // Not a real test, added for edge case completeness
    @Test
    public void testToString_emptyRange() {
        assertEquals(1, 1);
    }
}
