package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_boolean_contains_char_Test {

    private CharRange charRange;
    private CharRange negatedCharRange;

    @Before
    public void setUp() {
        charRange = CharRange.isIn('a', 'z');
        negatedCharRange = CharRange.isNotIn('a', 'z');
    }

    @Test
    public void testContainsWithinRange() {
        assertTrue(charRange.contains('a'));
        assertTrue(charRange.contains('m'));
        assertTrue(charRange.contains('z'));
    }

    @Test
    public void testContainsOutsideRange() {
        assertFalse(charRange.contains('A'));
        assertFalse(charRange.contains('0'));
        assertFalse(charRange.contains('!'));
    }

    @Test
    public void testContainsNegated() {
        assertFalse(negatedCharRange.contains('a'));
        assertFalse(negatedCharRange.contains('m'));
        assertFalse(negatedCharRange.contains('z'));
        assertTrue(negatedCharRange.contains('A'));
        assertTrue(negatedCharRange.contains('0'));
    }

    @Test
    public void testContainsEmptyRange() {
        CharRange emptyRange = CharRange.isIn('x', 'x');
        assertTrue(emptyRange.contains('x'));
        assertFalse(emptyRange.contains('y'));
    }

    @Test
    public void testContainsAllRange() {
        CharRange fullRange = CharRange.isIn(Character.MIN_VALUE, Character.MAX_VALUE);
        assertTrue(fullRange.contains('a'));
        assertTrue(fullRange.contains('Z'));
        assertTrue(fullRange.contains(Character.MIN_VALUE));
        assertTrue(fullRange.contains(Character.MAX_VALUE));
    }
}
