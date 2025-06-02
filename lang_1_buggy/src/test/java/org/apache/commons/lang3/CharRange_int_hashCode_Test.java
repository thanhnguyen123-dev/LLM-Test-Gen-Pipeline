package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_int_hashCode_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Assuming CharRange has a public constructor taking 'start', 'end', and 'negated'
        charRange = new CharRange('a', 'z', false);
    }

    @Test
    public void testTypicalRange() {
        CharRange range = new CharRange('a', 'z', false);
        int expectedHash = 83 + 'a' + 7 * 'z' + 0;
        assertEquals(expectedHash, range.hashCode());
    }

    @Test
    public void testNegatedRange() {
        CharRange range = new CharRange('a', 'z', true);
        int expectedHash = 83 + 'a' + 7 * 'z' + 1;
        assertEquals(expectedHash, range.hashCode());
    }

    @Test
    public void testSingleCharacterRange() {
        CharRange range = new CharRange('a', 'a', false);
        int expectedHash = 83 + 'a' + 7 * 'a' + 0;
        assertEquals(expectedHash, range.hashCode());
    }

    @Test
    public void testSingleCharacterNegatedRange() {
        CharRange range = new CharRange('a', 'a', true);
        int expectedHash = 83 + 'a' + 7 * 'a' + 1;
        assertEquals(expectedHash, range.hashCode());
    }

    @Test
    public void testBoundaryValues() {
        CharRange range = new CharRange(Character.MIN_VALUE, Character.MAX_VALUE, false);
        int expectedHash = 83 + Character.MIN_VALUE + 7 * Character.MAX_VALUE + 0;
        assertEquals(expectedHash, range.hashCode());
    }

    @Test
    public void testBoundaryValuesNegated() {
        CharRange range = new CharRange(Character.MIN_VALUE, Character.MAX_VALUE, true);
        int expectedHash = 83 + Character.MIN_VALUE + 7 * Character.MAX_VALUE + 1;
        assertEquals(expectedHash, range.hashCode());
    }
}
