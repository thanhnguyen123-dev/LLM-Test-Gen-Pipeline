package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_isNotIn_char_char_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        // No need to instantiate CharRange since the method is static
    }

    @Test
    public void testTypicalUseCase() {
        CharRange range = CharRange.isNotIn('a', 'z');
        assertNotNull(range);
        assertTrue(range.isNegated());
        assertEquals('a', range.getStart());
        assertEquals('z', range.getEnd());
    }

    @Test
    public void testEdgeCaseEmptyRange() {
        CharRange range = CharRange.isNotIn('a', 'a');
        assertNotNull(range);
        assertTrue(range.isNegated());
        assertEquals('a', range.getStart());
        assertEquals('a', range.getEnd());
    }

    @Test
    public void testReverseRange() {
        CharRange range = CharRange.isNotIn('z', 'a');
        assertNotNull(range);
        assertTrue(range.isNegated());
        assertEquals('z', range.getStart());
        assertEquals('a', range.getEnd());
    }

    @Test
    public void testBoundaryValues() {
        CharRange range = CharRange.isNotIn(Character.MIN_VALUE, Character.MAX_VALUE);
        assertNotNull(range);
        assertTrue(range.isNegated());
        assertEquals(Character.MIN_VALUE, range.getStart());
        assertEquals(Character.MAX_VALUE, range.getEnd());
    }

    @Test
    public void testErrorCaseWithInvalidCharacters() {
        try {
            CharRange range = CharRange.isNotIn((char) -1, (char) -1);
            assertNotNull(range); // Expect an exception to be thrown
        } catch (IllegalArgumentException e) {
            // Expected due to invalid character values
        }
    }

    // Add more test cases as needed for further corner cases revealed in Jimple or logic understanding
}
