package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_equals_Object_Test {

    private CharRange charRange1;
    private CharRange charRange2;

    @Before
    public void setUp() {
        charRange1 = new CharRange('a', 'z');
        charRange2 = new CharRange('a', 'm');
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(charRange1.equals(charRange1));
    }

    @Test
    public void testEquals_Null() {
        assertFalse(charRange1.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(charRange1.equals("not a CharRange"));
    }

    @Test
    public void testEquals_EqualObjects() {
        CharRange other = new CharRange('a', 'z');
        assertTrue(charRange1.equals(other));
    }

    @Test
    public void testEquals_DifferentStart() {
        assertFalse(charRange1.equals(charRange2));
    }

    @Test
    public void testEquals_DifferentEnd() {
        CharRange other = new CharRange('a', 'y');
        assertFalse(charRange1.equals(other));
    }

    @Test
    public void testEquals_DifferentNegation() {
        CharRange negatedCharRange = new CharRange('a', 'z', true);
        assertFalse(charRange1.equals(negatedCharRange));
    }
}
