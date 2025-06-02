package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_boolean_equals_Object_Test {

    private CharRange charRangeA;
    private CharRange charRangeB;
    private CharRange charRangeC;

    @Before
    public void setUp() {
        charRangeA = CharRange.is('a');
        charRangeB = CharRange.isIn('a', 'z');
        charRangeC = CharRange.isNot('a');
    }

    @Test
    public void testEquals_sameObject() {
        assertTrue(charRangeA.equals(charRangeA));
    }

    @Test
    public void testEquals_equalRanges() {
        CharRange range = CharRange.is('a');
        assertTrue(charRangeA.equals(range));
    }

    @Test
    public void testEquals_differentStart() {
        assertFalse(charRangeA.equals(charRangeB));
    }

    @Test
    public void testEquals_differentEnd() {
        CharRange range = CharRange.isIn('a', 'y');
        assertFalse(charRangeB.equals(range));
    }

    @Test
    public void testEquals_differentNegation() {
        assertFalse(charRangeA.equals(charRangeC));
    }

    @Test
    public void testEquals_nullObject() {
        assertFalse(charRangeA.equals(null));
    }

    @Test
    public void testEquals_differentObjectType() {
        assertFalse(charRangeA.equals(new Object()));
    }
}
