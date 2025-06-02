package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_Char_isNot_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Setup any necessary resources before each test if needed
    }

    @Test
    public void testIsNotWithTypicalValue() {
        charRange = CharRange.isNot('a');
        assertTrue(charRange.contains('b'));
        assertFalse(charRange.contains('a'));
    }

    @Test
    public void testIsNotWithBoundaryValue() {
        charRange = CharRange.isNot(Character.MAX_VALUE);
        assertTrue(charRange.contains(Character.MAX_VALUE - 1));
        assertFalse(charRange.contains(Character.MAX_VALUE));

        charRange = CharRange.isNot(Character.MIN_VALUE);
        assertTrue(charRange.contains((char) (Character.MIN_VALUE + 1)));
        assertFalse(charRange.contains(Character.MIN_VALUE));
    }

    @Test
    public void testIsNotWithEdgeCase() {
        charRange = CharRange.isNot(' ');
        assertTrue(charRange.contains('a'));
        assertFalse(charRange.contains(' '));
    }

    // Testing null inputs is not applicable to primitive types like char
}
