package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_is_char_Test {

    private char singleChar;
    private CharRange charRange;

    @Before
    public void setUp() {
        this.singleChar = 'a';
        this.charRange = CharRange.is(singleChar);
    }

    @Test
    public void testSingleCharInRange() {
        assertTrue(charRange.contains(singleChar));
    }

    @Test
    public void testCharNotInRange() {
        assertFalse(charRange.contains('b'));
    }

    @Test
    public void testRangeWithSameChar() {
        CharRange sameCharRange = CharRange.is('a');
        assertEquals(charRange, sameCharRange);
    }

    @Test
    public void testEdgeCaseMinChar() {
        CharRange minCharRange = CharRange.is(Character.MIN_VALUE);
        assertTrue(minCharRange.contains(Character.MIN_VALUE));
        assertFalse(minCharRange.contains('a'));
    }

    @Test
    public void testEdgeCaseMaxChar() {
        CharRange maxCharRange = CharRange.is(Character.MAX_VALUE);
        assertTrue(maxCharRange.contains(Character.MAX_VALUE));
        assertFalse(maxCharRange.contains('a'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {
        CharRange nullCharRange = CharRange.is((Character) null);
    }
}
