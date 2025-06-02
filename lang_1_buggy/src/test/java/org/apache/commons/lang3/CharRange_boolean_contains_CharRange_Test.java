package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_boolean_contains_CharRange_Test {

    private CharRange range1;
    private CharRange range2;

    @Before
    public void setUp() {
        range1 = CharRange.is('a');
        range2 = CharRange.is('b');
    }

    @Test
    public void testContains_SameRange() {
        CharRange range = CharRange.is('a');
        assertTrue(range.contains(range));
    }

    @Test
    public void testContains_NullRange() {
        try {
            range1.contains(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Range must not be null", e.getMessage());
        }
    }

    @Test
    public void testContains_DifferentRanges_NonNegated() {
        CharRange rangeA = CharRange.isIn('a', 'c');
        CharRange rangeB = CharRange.is('b');
        assertTrue(rangeA.contains(rangeB));
    }

    @Test
    public void testContains_DifferentRanges_Negated() {
        CharRange rangeA = CharRange.isNotIn('d', 'f');
        CharRange rangeB = CharRange.isIn('a', 'z');
        assertFalse(rangeA.contains(rangeB));
    }

    @Test
    public void testContains_BoundaryCondition() {
        CharRange rangeA = CharRange.is('a');
        CharRange rangeB = CharRange.is('a');
        assertTrue(rangeA.contains(rangeB));
    }

    @Test
    public void testContains_OutOfRange() {
        CharRange rangeA = CharRange.is('a');
        CharRange rangeB = CharRange.is('b');
        assertFalse(rangeA.contains(rangeB));
    }
}
