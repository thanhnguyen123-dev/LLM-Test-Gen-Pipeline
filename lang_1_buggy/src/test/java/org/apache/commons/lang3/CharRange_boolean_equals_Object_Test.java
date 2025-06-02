package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharRange_boolean_equals_Object_Test {

    private CharRange charRange1;
    private CharRange charRange2;
    private CharRange charRange3;

    @Before
    public void setUp() {
        // Create CharRange instances for testing
        charRange1 = CharRange.is('a');
        charRange2 = CharRange.is('a');
        charRange3 = CharRange.is('b');
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(charRange1.equals(charRange1));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(charRange1.equals(null));
    }

    @Test
    public void testEquals_DifferentClassObject() {
        assertFalse(charRange1.equals("Some String"));
    }

    @Test
    public void testEquals_SameValues() {
        assertTrue(charRange1.equals(charRange2));
    }

    @Test
    public void testEquals_DifferentStartValues() {
        assertFalse(charRange1.equals(charRange3));
    }

    @Test
    public void testEquals_DifferentEndValues() {
        CharRange charRangeWithDiffEnd = CharRange.isIn('a', 'c');
        assertFalse(charRange1.equals(charRangeWithDiffEnd));
    }

    @Test
    public void testEquals_DifferentNegation() {
        CharRange negatedCharRange = CharRange.isNot('a');
        assertFalse(charRange1.equals(negatedCharRange));
    }
}
