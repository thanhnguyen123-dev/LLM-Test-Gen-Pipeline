package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_is_char_Test {

    @Test
    public void testCharRangeSingleCharacter() {
        CharRange result = CharRange.is('a');
        assertNotNull(result);
        assertEquals('a', result.getStart());
        assertEquals('a', result.getEnd());
        assertFalse(result.isNegated());
    }

    @Test
    public void testCharRangeWithDifferentCharacter() {
        CharRange result = CharRange.is('z');
        assertNotNull(result);
        assertEquals('z', result.getStart());
        assertEquals('z', result.getEnd());
        assertFalse(result.isNegated());
    }
}
