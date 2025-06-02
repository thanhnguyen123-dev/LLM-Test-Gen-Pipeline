package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_int_hashCode_Test {

    private CharRange range1;
    private CharRange range2;
    private CharRange range3;
    private CharRange range4;

    @Before
    public void setUp() {
        range1 = CharRange.is('a');
        range2 = CharRange.isNot('b');
        range3 = CharRange.isIn('c', 'd');
        range4 = CharRange.isNotIn('e', 'f');
    }

    @Test
    public void testHashCode_SameCharRange_ShouldHaveSameHashCode() {
        CharRange otherRange = CharRange.is('a');
        assertEquals(range1.hashCode(), otherRange.hashCode());
    }

    @Test
    public void testHashCode_DifferentCharRanges_ShouldHaveDifferentHashCodes() {
        assertNotEquals(range1.hashCode(), range2.hashCode());
        assertNotEquals(range1.hashCode(), range3.hashCode());
        assertNotEquals(range2.hashCode(), range3.hashCode());
    }

    @Test
    public void testHashCode_SameRangeDifferentNegation_ShouldHaveDifferentHashCodes() {
        CharRange otherRange = CharRange.isNot('a');
        assertNotEquals(range1.hashCode(), otherRange.hashCode());
    }
}
