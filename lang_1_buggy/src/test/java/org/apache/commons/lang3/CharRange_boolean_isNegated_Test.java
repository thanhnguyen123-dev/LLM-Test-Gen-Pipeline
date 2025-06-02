package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_boolean_isNegated_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Use factory methods since constructor is private
        charRange = CharRange.isNot('a');
    }

    @Test
    public void testIsNegated_PositiveCase() {
        // Scenario where range is negated
        assertTrue(charRange.isNegated());
    }

    @Test
    public void testIsNegated_NegativeCase() {
        // Creating a non-negated range
        CharRange nonNegatedRange = CharRange.is('a');
        assertFalse(nonNegatedRange.isNegated());
    }
}
