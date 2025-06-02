package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class CharRange_boolean_contains_CharRange_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = new CharRange('a', 'z');
    }

    @Test
    public void testContainsTypicalCase() {
        CharRange other = new CharRange('b', 'y');
        assertTrue(charRange.contains(other));
    }

    @Test
    public void testContainsEdgeCase_ExactMatch() {
        CharRange other = new CharRange('a', 'z');
        assertTrue(charRange.contains(other));
    }

    @Test
    public void testContainsEdgeCase_SingleCharacter() {
        CharRange other = new CharRange('c');
        assertTrue(charRange.contains(other));
    }

    @Test
    public void testContainsEmptyRange() {
        CharRange other = new CharRange('a', 'a');
        assertTrue(charRange.contains(other));
    }

    @Test
    public void testDoesNotContain() {
        CharRange other = new CharRange('A', 'Z');
        assertFalse(charRange.contains(other));
    }

    @Test
    public void testContainsWithNullInput() {
        try {
            charRange.contains(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Exception expected
        }
    }

    @Test
    public void testNegatedRangeContains() {
        CharRange negatedRange = CharRange.isNot('a');
        CharRange other = new CharRange('b', 'y');
        assertTrue(negatedRange.contains(other));
    }

    @Test
    public void testNegatedAndNonNegatedRange() {
        CharRange negatedRange = CharRange.isNot('a');
        CharRange conflictingRange = CharRange.is('z');
        assertFalse(negatedRange.contains(conflictingRange));
    }

    @Test
    public void testBoundaryValues() {
        CharRange boundaryRange = new CharRange(Character.MIN_VALUE, Character.MAX_VALUE);
        assertTrue(boundaryRange.contains(charRange));
    }
}
