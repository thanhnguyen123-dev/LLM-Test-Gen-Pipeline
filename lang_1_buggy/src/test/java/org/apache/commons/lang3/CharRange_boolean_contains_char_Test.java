package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_boolean_contains_char_Test {

    private CharRange rangeNonNegated;
    private CharRange rangeNegated;

    @Before
    public void setUp() {
        rangeNonNegated = CharRange.is('a', 'z');
        rangeNegated = CharRange.isNot('a', 'z');
    }

    @Test
    public void testContainsTypicalCase() {
        assertTrue(rangeNonNegated.contains('c'));
        assertFalse(rangeNegated.contains('c'));
    }

    @Test
    public void testContainsEdgeCaseStart() {
        assertTrue(rangeNonNegated.contains('a'));
        assertFalse(rangeNegated.contains('a'));
    }

    @Test
    public void testContainsEdgeCaseEnd() {
        assertTrue(rangeNonNegated.contains('z'));
        assertFalse(rangeNegated.contains('z'));
    }

    @Test
    public void testContainsBeforeStart() {
        assertFalse(rangeNonNegated.contains('`'));
        assertTrue(rangeNegated.contains('`'));
    }

    @Test
    public void testContainsAfterEnd() {
        assertFalse(rangeNonNegated.contains('{'));
        assertTrue(rangeNegated.contains('{'));
    }

    @Test
    public void testContainsSingleCharRange() {
        CharRange singleCharRange = CharRange.is('m');
        assertTrue(singleCharRange.contains('m'));
        assertFalse(singleCharRange.contains('n'));
    }
}
