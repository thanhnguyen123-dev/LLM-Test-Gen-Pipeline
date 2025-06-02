package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_iterator_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = new CharRange('a', 'z');
    }

    @Test
    public void testIteratorTypicalCase() {
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('a'), iterator.next());
    }

    @Test
    public void testIteratorEdgeCaseEmptyRange() {
        charRange = new CharRange('a', 'a');
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('a'), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNullHandling() {
        try {
            CharRange nullRange = null;
            Iterator<Character> iterator = nullRange.iterator();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testIteratorNegatedRange() {
        charRange = new CharRange('a', 'z', true);
        Iterator<Character> iterator = charRange.iterator();
        assertTrue(iterator.hasNext());
        assertNotEquals(Character.valueOf('a'), iterator.next());
    }
}
