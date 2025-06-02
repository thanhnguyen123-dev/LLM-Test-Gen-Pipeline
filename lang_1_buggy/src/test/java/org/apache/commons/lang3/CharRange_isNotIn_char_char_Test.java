package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_isNotIn_char_char_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // Set up any common test data if needed
    }

    @Test
    public void testTypicalUseCase() {
        char start = 'a';
        char end = 'z';
        CharRange result = CharRange.isNotIn(start, end);

        assertNotNull(result);
        assertTrue(result.isNegated());
        assertEquals(start, result.getStart());
        assertEquals(end, result.getEnd());
    }

    @Test
    public void testEdgeCaseSingleCharacter() {
        char start = 'a';
        char end = 'a';
        CharRange result = CharRange.isNotIn(start, end);

        assertNotNull(result);
        assertTrue(result.isNegated());
        assertEquals(start, result.getStart());
        assertEquals(end, result.getEnd());
    }

    @Test
    public void testErrorCaseNullCharRange() {
        try {
            CharRange result = CharRange.isNotIn('\0', '\0');
            assertNotNull(result);
        } catch (Exception e) {
            fail("Exception should not be thrown for null char range: " + e.getMessage());
        }
    }

    @Test
    public void testBoundaryCaseMaxAndMinValue() {
        char start = Character.MIN_VALUE;
        char end = Character.MAX_VALUE;

        CharRange result = CharRange.isNotIn(start, end);

        assertNotNull(result);
        assertTrue(result.isNegated());
        assertEquals(start, result.getStart());
        assertEquals(end, result.getEnd());
    }

    @Test
    public void testTypicalInverseRange() {
        char start = 'z';
        char end = 'a';

        CharRange result = CharRange.isNotIn(start, end);

        assertNotNull(result);
        assertTrue(result.isNegated());
        // Test when start is greater than end
        assertEquals(start, result.getStart());
        assertEquals(end, result.getEnd());
    }
}
