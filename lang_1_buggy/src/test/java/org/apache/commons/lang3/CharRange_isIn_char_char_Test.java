package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_isIn_char_char_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Use factory method or public constructor to instantiate if necessary
        // Unable to instantiate CharRange as constructor is private, testing static methods.
    }

    @Test
    public void testTypicalCase_WithinRange() {
        char start = 'a';
        char end = 'z';
        charRange = CharRange.isIn(start, end);

        assertTrue(charRange.contains('m'));
    }

    @Test
    public void testTypicalCase_ExactStart() {
        char start = 'a';
        char end = 'z';
        charRange = CharRange.isIn(start, end);

        assertTrue(charRange.contains(start));
    }

    @Test
    public void testTypicalCase_ExactEnd() {
        char start = 'a';
        char end = 'z';
        charRange = CharRange.isIn(start, end);

        assertTrue(charRange.contains(end));
    }

    @Test
    public void testEdgeCase_StartGreaterThanEnd() {
        char start = 'z';
        char end = 'a';
        charRange = CharRange.isIn(start, end);

        assertFalse(charRange.contains('m'));
    }

    @Test
    public void testEdgeCase_EmptyRange() {
        char start = 'a';
        char end = 'a';
        charRange = CharRange.isIn(start, end);

        assertTrue(charRange.contains('a'));
        assertFalse(charRange.contains('b'));
    }

    @Test
    public void testErrorCase_NullCharacter() {
        try {
            charRange = CharRange.isIn('\0', '\0');
            assertFalse(charRange.contains('a'));
            assertTrue(charRange.contains('\0'));
        } catch (Exception e) {
            fail("Exception should not be thrown for null character");
        }
    }
}
