package org.apache.commons.lang3;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharRange_iterator_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = CharRange.is('a'); // Assuming 'is(char)' is a typical factory method to create CharRange
    }

    @Test
    public void testIteratorTypicalCase() {
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('a'), iterator.next());
    }

    @Test
    public void testIteratorEndBoundary() {
        charRange = CharRange.isIn('a', 'c');
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('a'), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('b'), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('c'), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorEmptyRange() {
        charRange = CharRange.isIn('a', 'a');
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('a'), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Iterator<Character> iterator = charRange.iterator();
        iterator.next();
        iterator.remove();
    }

    @Test
    public void testIteratorNegated() {
        charRange = CharRange.isNot('a');
        Iterator<Character> iterator = charRange.iterator();
        // depending on definition, negated ranges need specific handling
        // typically CharRange representing the complete set would be expected
    }

    @Test
    public void testIteratorForAllCharacters() {
        charRange = CharRange.isIn(Character.MIN_VALUE, Character.MAX_VALUE);
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
    }
}
