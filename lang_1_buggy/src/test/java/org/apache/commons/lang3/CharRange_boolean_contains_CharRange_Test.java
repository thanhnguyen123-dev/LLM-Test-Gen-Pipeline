package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharRange_boolean_contains_CharRange_Test {

    private CharRange rangeA;
    private CharRange rangeB;
    private CharRange rangeC;
    private CharRange rangeD;
    private CharRange negatedRange;

    @Before
    public void setUp() {
        rangeA = CharRange.is('a');
        rangeB = CharRange.isIn('a', 'z');
        rangeC = CharRange.is('z');
        rangeD = CharRange.isIn('m', 'n');
        negatedRange = CharRange.isNotIn('m', 'n');
    }

    @Test
    public void testContainsTypical() {
        assertTrue(rangeB.contains(rangeA));
        assertTrue(rangeB.contains(rangeC));
        assertTrue(rangeB.contains(rangeB));
        assertFalse(rangeD.contains(rangeA));
    }

    @Test
    public void testContainsNegated() {
        assertTrue(negatedRange.contains(rangeA));
        assertFalse(negatedRange.contains(rangeD));
    }

    @Test
    public void testContainsNullRange() {
        try {
            rangeA.contains(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Range must not be null", e.getMessage());
        }
    }

    @Test
    public void testContainsEdgeCases() {
        CharRange fullRange = CharRange.isIn('\0', (char) 65535);
        assertTrue(fullRange.contains(rangeA));
        assertTrue(fullRange.contains(negatedRange));
    }
}
