package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_toString_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Typical use case: normal range
        charRange = new CharRange('a', 'z');
    }

    @Test
    public void testToString_TypicalRange() {
        String result = charRange.toString();
        assertEquals("az", result);
    }

    @Test
    public void testToString_SingleCharRange() {
        charRange = new CharRange('a', 'a');
        String result = charRange.toString();
        assertEquals("a", result);
    }

    @Test
    public void testToString_NegatedRange() {
        charRange = new CharRange('a', 'z', true);
        String result = charRange.toString();
        assertEquals("^az", result);
    }

    @Test
    public void testToString_EmptyRange() {
        charRange = new CharRange('a', 'a', true);
        String result = charRange.toString();
        assertEquals("^a", result);
    }

    @Test
    public void testToString_MinMaxValues() {
        charRange = new CharRange(Character.MIN_VALUE, Character.MAX_VALUE);
        String result = charRange.toString();
        assertEquals("" + Character.MIN_VALUE + "-", result.substring(0, 2));
        // Note: Full representation check might be impractical due to large output
    }

    @Test
    public void testToString_SetupReuse() {
        // Additional case using same instance from setUp
        String result = charRange.toString();
        assertEquals("az", result);
    }
}
