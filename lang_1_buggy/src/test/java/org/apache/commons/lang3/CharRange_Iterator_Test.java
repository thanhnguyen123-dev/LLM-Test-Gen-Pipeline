package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Iterator;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_Iterator_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = new CharRange('a', 'c');
    }

    @Test
    public void testIteratorTypicalUseCase() {
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
    public void testIteratorSingleElement() {
        charRange = new CharRange('a', 'a');
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('a'), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorEdgeCaseEmptyRange() {
        charRange = new CharRange('b', 'a'); // typically invalid range in some implementations
        Iterator<Character> iterator = charRange.iterator();
        assertFalse(iterator.hasNext());
    }
}
