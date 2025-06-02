package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CharSetUtils_int_count_String_StringArray_Test {

    @Test
    public void testCount_NullString() {
        int result = CharSetUtils.count(null, new String[]{"a"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_EmptyString() {
        int result = CharSetUtils.count("", new String[]{"a"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_StringNullSet() {
        int result = CharSetUtils.count("hello", null);
        assertEquals(0, result);
    }

    @Test
    public void testCount_StringEmptySet() {
        int result = CharSetUtils.count("hello", new String[]{""});
        assertEquals(0, result);
    }

    @Test
    public void testCount_ExampleTest1() {
        int result = CharSetUtils.count("hello", new String[]{"k-p"});
        assertEquals(3, result);
    }

    @Test
    public void testCount_ExampleTest2() {
        int result = CharSetUtils.count("hello", new String[]{"a-e"});
        assertEquals(1, result);
    }

    @Test
    public void testCount_NoMatch() {
        int result = CharSetUtils.count("abc", new String[]{"x-z"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_AllMatch() {
        int result = CharSetUtils.count("abc", new String[]{"a-c"});
        assertEquals(3, result);
    }

    @Test
    public void testCount_EmptySetArray() {
        int result = CharSetUtils.count("hello", new String[0]);
        assertEquals(0, result);
    }
}
