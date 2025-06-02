package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharRange_char_getEnd_Test {

    private CharRange charRange1;
    private CharRange charRange2;

    @Before
    public void setUp() {
        // Assuming the existence of a factory method based on given `class_factory_methods`.
        charRange1 = CharRange.is('a');
        charRange2 = CharRange.isIn('b', 'z');
    }

    @Test
    public void testGetEnd_typicalRange() {
        assertEquals('a', charRange1.getEnd());
    }

    @Test
    public void testGetEnd_differentRange() {
        assertEquals('z', charRange2.getEnd());
    }

    @Test
    public void testGetEnd_edgeCase() {
        CharRange charRangeEdge = CharRange.is(Character.MAX_VALUE);
        assertEquals(Character.MAX_VALUE, charRangeEdge.getEnd());
    }
}
