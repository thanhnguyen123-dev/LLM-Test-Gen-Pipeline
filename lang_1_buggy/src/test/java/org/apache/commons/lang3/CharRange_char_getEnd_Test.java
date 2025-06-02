package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_char_getEnd_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = CharRange.is('a'); // Using static factory method to instantiate.
    }

    @Test
    public void testTypicalUseCase() {
        assertEquals('a', charRange.getEnd());
    }

    @Test
    public void testEdgeCase_EmptyRange() {
        charRange = CharRange.is('\0'); // Check with zero/null char value
        assertEquals('\0', charRange.getEnd());
    }

    @Test
    public void testEdgeCase_MaxValue() {
        charRange = CharRange.is(Character.MAX_VALUE); // Check with max char value
        assertEquals(Character.MAX_VALUE, charRange.getEnd());
    }
}
