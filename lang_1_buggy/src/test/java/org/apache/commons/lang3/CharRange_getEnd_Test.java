package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_getEnd_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Assume there exists a constructor for CharRange taking start, end, and negated flag.
        charRange = new CharRange('a', 'z', false);
    }

    @Test
    public void testGetEnd() {
        assertEquals('z', charRange.getEnd());
    }

    @Test
    public void testGetEndSingleCharacterRange() {
        charRange = new CharRange('a', 'a', false);
        assertEquals('a', charRange.getEnd());
    }

    @Test
    public void testGetEndNegativeRange() {
        charRange = new CharRange('z', 'a', true);
        assertEquals('a', charRange.getEnd());
    }

    @Test
    public void testGetEndBoundaryValue() {
        charRange = new CharRange(Character.MIN_VALUE, Character.MAX_VALUE, false);
        assertEquals(Character.MAX_VALUE, charRange.getEnd());
    }
}
