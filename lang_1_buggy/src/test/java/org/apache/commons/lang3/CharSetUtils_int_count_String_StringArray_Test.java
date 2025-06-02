package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CharSetUtils_int_count_String_StringArray_Test {

    @Test
    public void testCount_nullString() {
        int result = CharSetUtils.count(null, new String[]{"a-e"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_emptyString() {
        int result = CharSetUtils.count("", new String[]{"a-e"});
        assertEquals(0, result);
    }

    @Test
    public void testCount_nullSet() {
        int result = CharSetUtils.count("hello", null);
        assertEquals(0, result);
    }

    @Test
    public void testCount_emptySet() {
        int result = CharSetUtils.count("hello", new String[]{""});
        assertEquals(0, result);
    }

    @Test
    public void testCount_typicalCase() {
        int result = CharSetUtils.count("hello", new String[]{"k-p"});
        assertEquals(3, result);
    }

    @Test
    public void testCount_edgeCase() {
        int result = CharSetUtils.count("hello", new String[]{"a-e"});
        assertEquals(1, result);
    }

    @Test
    public void testCount_noMatchingCharacters() {
        int result = CharSetUtils.count("world", new String[]{"a-e"});
        assertEquals(0, result);
    }
}
