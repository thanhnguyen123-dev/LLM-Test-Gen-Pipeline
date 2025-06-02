package org.apache.commons.lang3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CharRange_isNegated_Test {

    private CharRange negatedRange;
    private CharRange nonNegatedRange;

    @Before
    public void setUp() {
        // Assuming CharRange has public constructors like CharRange(char, char, boolean)
        negatedRange = new CharRange('a', 'z', true);
        nonNegatedRange = new CharRange('a', 'z', false);
    }

    @Test
    public void testIsNegatedTrue() {
        assertTrue(negatedRange.isNegated());
    }

    @Test
    public void testIsNegatedFalse() {
        assertFalse(nonNegatedRange.isNegated());
    }
}
