package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharRange_char_getStart_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Instantiate CharRange using a factory method since the constructor is private
        charRange = CharRange.is('a');
    }

    @Test
    public void testGetStartWithTypicalRange() {
        assertEquals('a', charRange.getStart());
    }

    @Test
    public void testGetStartWithRangeFromFactoryMethodIsNot() {
        charRange = CharRange.isNot('z');
        assertEquals(Character.MIN_VALUE, charRange.getStart());
    }

    @Test
    public void testGetStartWithEdgeCaseMinChar() {
        charRange = CharRange.is(Character.MIN_VALUE);
        assertEquals(Character.MIN_VALUE, charRange.getStart());
    }

    @Test
    public void testGetStartWithEdgeCaseMaxChar() {
        charRange = CharRange.is(Character.MAX_VALUE);
        assertEquals(Character.MAX_VALUE, charRange.getStart());
    }

    @Test
    public void testGetStartWithNonNegatedRange() {
        charRange = CharRange.is('a');
        assertEquals('a', charRange.getStart());
    }
}
