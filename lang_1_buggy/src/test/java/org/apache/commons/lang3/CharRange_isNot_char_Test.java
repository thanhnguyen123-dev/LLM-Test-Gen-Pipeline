package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_isNot_char_Test {

    @Test
    public void testTypicalCase() {
        CharRange result = CharRange.isNot('a');
        assertTrue(result.contains('b'));
        assertFalse(result.contains('a'));
    }

    @Test
    public void testBoundaryCase() {
        CharRange result = CharRange.isNot(Character.MIN_VALUE);
        assertFalse(result.contains(Character.MIN_VALUE));
        assertTrue(result.contains((char)(Character.MIN_VALUE + 1)));
    }

    @Test
    public void testSingleCharacterBoundary() {
        CharRange result = CharRange.isNot('z');
        assertFalse(result.contains('z'));
        assertTrue(result.contains((char) ('z' - 1)));
    }

    @Test
    public void testNullCharacterCase() {
        CharRange result = CharRange.isNot('\0');
        assertFalse(result.contains('\0'));
        assertTrue(result.contains('x'));
    }
}
