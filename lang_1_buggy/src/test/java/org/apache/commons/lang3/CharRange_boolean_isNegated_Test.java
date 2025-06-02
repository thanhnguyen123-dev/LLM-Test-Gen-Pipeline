package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CharRange_boolean_isNegated_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Initialize using public static factory methods
        charRange = CharRange.is('a'); // Typical initialization, not negated
    }

    @Test
    public void testIsNegated_TypicalCase() {
        assertEquals(false, charRange.isNegated());
    }

    @Test
    public void testIsNegated_NegatedCase() {
        CharRange negatedRange = CharRange.isNot('a');
        assertEquals(true, negatedRange.isNegated());
    }
}