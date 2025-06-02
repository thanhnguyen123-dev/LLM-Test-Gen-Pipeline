package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_string_squeeze_String_Test {

    @Before
    public void setUp() {
        // Any necessary setup can be done here if needed
    }

    @Test
    public void testSqueezeNullString() {
        String result = CharSetUtils.squeeze(null, new String[]{"a-z"});
        assertNull(result);
    }

    @Test
    public void testSqueezeEmptyString() {
        String result = CharSetUtils.squeeze("", new String[]{"a-z"});
        assertEquals("", result);
    }

    @Test
    public void testSqueezeNullSet() {
        String result = CharSetUtils.squeeze("hello", null);
        assertEquals("hello", result);
    }

    @Test
    public void testSqueezeEmptySet() {
        String result = CharSetUtils.squeeze("hello", new String[]{""});
        assertEquals("hello", result);
    }

    @Test
    public void testSqueezeTypicalCase() {
        String result = CharSetUtils.squeeze("hello", new String[]{"k-p"});
        assertEquals("helo", result);
    }

    @Test
    public void testSqueezeNoRepetitions() {
        String result = CharSetUtils.squeeze("hello", new String[]{"a-e"});
        assertEquals("hello", result);
    }

    @Test
    public void testSqueezeSingleCharacter() {
        String result = CharSetUtils.squeeze("aaabbb", new String[]{"a"});
        assertEquals("abbb", result);
    }

    @Test
    public void testSqueezeNonMatchingSet() {
        String result = CharSetUtils.squeeze("abc", new String[]{"x-z"});
        assertEquals("abc", result);
    }

    @Test
    public void testSqueezeAllCharacters() {
        String result = CharSetUtils.squeeze("aaa", new String[]{"a"});
        assertEquals("a", result);
    }

    @Test
    public void testSqueezeBoundaryValues() {
        String result = CharSetUtils.squeeze(String.valueOf(Character.MAX_VALUE), new String[]{String.valueOf(Character.MAX_VALUE)});
        assertEquals(String.valueOf(Character.MAX_VALUE), result);
    }
}
