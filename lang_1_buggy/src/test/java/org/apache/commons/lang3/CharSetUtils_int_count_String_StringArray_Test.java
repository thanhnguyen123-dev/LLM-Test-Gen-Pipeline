package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CharSetUtils_int_count_String_StringArray_Test {

    @Before
    public void setUp() {
        // No setup needed for static method
    }

    @Test
    public void testCount_NullString() {
        int result = CharSetUtils.count(null, new String[]{"a-e"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_EmptyString() {
        int result = CharSetUtils.count("", new String[]{"a-e"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_NullSet() {
        int result = CharSetUtils.count("hello", null);
        assertEquals(0, result);
    }

    @Test
    public void testCount_EmptySet() {
        int result = CharSetUtils.count("hello", new String[]{});
        assertEquals(0, result);
    }

    @Test
    public void testCount_TypicalCase() {
        int result = CharSetUtils.count("hello", new String[]{"a-e"});
        assertEquals(1, result);
    }

    @Test
    public void testCount_MultipleRange() {
        int result = CharSetUtils.count("hello", new String[]{"k-p"});
        assertEquals(3, result);
    }

    @Test
    public void testCount_NoMatch() {
        int result = CharSetUtils.count("xyz", new String[]{"a-e"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_FullAlphabet() {
        int result = CharSetUtils.count("abcdefghijklmnopqrstuvwxyz", new String[]{"a-z"});
        assertEquals(26, result);
    }

    @Test
    public void testCount_OverlappingRanges() {
        int result = CharSetUtils.count("hello world", new String[]{"a-m", "m-z"});
        assertEquals(8, result);
    }
}
