package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharRange_char_getStart_Test {

    private CharRange charRange;

    @Before
    public void setUp() {
        charRange = new CharRange('a', 'z');
    }

    @Test
    public void testGetStartTypical() {
        assertEquals('a', charRange.getStart());
    }

    @Test
    public void testGetStartSingleCharRange() {
        CharRange singleCharRange = new CharRange('x');
        assertEquals('x', singleCharRange.getStart());
    }

    @Test
    public void testGetStartEmptyRange() {
        CharRange emptyRange = CharRange.is(' ');
        assertEquals(' ', emptyRange.getStart());
    }
}
