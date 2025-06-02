package org.apache.commons.lang3;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharRange_is_char_Test {

    @Test
    public void testIsSingleCharacterRange() {
        CharRange charRange = CharRange.is('a');
        assertEquals('a', charRange.iterator().next().charValue());
        assertFalse(charRange.isNegated());
    }

    @Test
    public void testIsEdgeCharacter() {
        CharRange charRange = CharRange.is(Character.MIN_VALUE);
        assertEquals(Character.MIN_VALUE, charRange.iterator().next().charValue());
        assertFalse(charRange.isNegated());
    }

    @Test
    public void testIsMaxCharacter() {
        CharRange charRange = CharRange.is(Character.MAX_VALUE);
        assertEquals(Character.MAX_VALUE, charRange.iterator().next().charValue());
        assertFalse(charRange.isNegated());
    }

    @Test
    public void testIsEmptyInput() {
        // No empty input possible for char since it must take a character
        // This test should assert intended method behavior
    }
}
