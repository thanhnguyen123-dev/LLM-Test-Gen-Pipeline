package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharRange_boolean_contains_char_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = CharRange.is('a');
    }

    @Test
    public void testContainsTypical() {
        assertTrue(charRange.contains('a'));
    }

    @Test
    public void testContainsEdgeLower() {
        assertFalse(charRange.contains('`'));
    }

    @Test
    public void testContainsEdgeUpper() {
        assertFalse(charRange.contains('b'));
    }

    @Test
    public void testContainsBoundary() {
        assertTrue(charRange.contains('a'));
        assertFalse(charRange.contains('b'));
    }

    @Test
    public void testContainsNegatedRange() {
        CharRange negatedRange = CharRange.isNot('a');
        assertFalse(negatedRange.contains('a'));
        assertTrue(negatedRange.contains('b'));
    }
}
