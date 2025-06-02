package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class CharRange_isNot_char_Test {

    private char[] typicalChars;
    private char[] edgeChars;

    @Before
    public void setUp() {
        typicalChars = new char[] {'a', '1', '@', 'Z'};
        edgeChars = new char[] {Character.MIN_VALUE, Character.MAX_VALUE};
    }

    @Test
    public void testIsNot_TypicalChars() {
        for (char ch : typicalChars) {
            CharRange range = CharRange.isNot(ch);
            assertTrue(range.isNegated());
            assertEquals(ch, range.iterator().next().charValue());
        }
    }

    @Test
    public void testIsNot_EdgeChars() {
        for (char ch : edgeChars) {
            CharRange range = CharRange.isNot(ch);
            assertTrue(range.isNegated());
            assertEquals(ch, range.iterator().next().charValue());
        }
    }

    @Test
    public void testIsNot_MultipleChars() {
        CharRange range1 = CharRange.isNot('a');
        CharRange range2 = CharRange.isNot('z');
        assertNotEquals(range1, range2);
    }
}
