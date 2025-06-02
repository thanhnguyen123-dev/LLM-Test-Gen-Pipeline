package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CharRange_char_getStart_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = CharRange.is('a');
    }

    @Test
    public void testGetStartTypicalCase() {
        assertEquals('a', charRange.getStart());
    }

    @Test
    public void testGetStartEdgeCase() {
        CharRange edgeCaseRange = CharRange.is('\0');
        assertEquals('\0', edgeCaseRange.getStart());
    }

    @Test
    public void testGetStartBoundaryCase() {
        CharRange boundaryCaseRange = CharRange.is(Character.MAX_VALUE);
        assertEquals(Character.MAX_VALUE, boundaryCaseRange.getStart());
    }
}
