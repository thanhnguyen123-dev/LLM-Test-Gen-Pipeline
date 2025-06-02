package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CharRange_String_toString_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = CharRange.is('a');
    }

    @Test
    public void testSingleCharacterRange() {
        CharRange range = CharRange.is('a');
        assertEquals("a", range.toString());
    }

    @Test
    public void testRangeOfCharacters() {
        CharRange range = CharRange.isIn('a', 'z');
        assertEquals("a-z", range.toString());
    }

    @Test
    public void testNegatedSingleCharacterRange() {
        CharRange range = CharRange.isNot('a');
        assertEquals("^a", range.toString());
    }

    @Test
    public void testNegatedRangeOfCharacters() {
        CharRange range = CharRange.isNotIn('a', 'z');
        assertEquals("^a-z", range.toString());
    }

    @Test
    public void testCachedStringRepresentation() {
        CharRange range = CharRange.is('a');
        String firstCall = range.toString();
        String secondCall = range.toString();
        assertEquals(firstCall, secondCall);
    }
}
