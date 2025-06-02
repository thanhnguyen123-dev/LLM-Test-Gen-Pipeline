package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharSetUtils_String_delete_String_StringArray_Test {

    @Test
    public void testDeleteWithNullString() {
        assertNull(CharSetUtils.delete(null, new String[]{"a", "b"}));
    }

    @Test
    public void testDeleteWithEmptyString() {
        assertEquals("", CharSetUtils.delete("", new String[]{"a", "b"}));
    }

    @Test
    public void testDeleteWithNullSet() {
        assertEquals("hello", CharSetUtils.delete("hello", null));
    }

    @Test
    public void testDeleteWithEmptySet() {
        assertEquals("hello", CharSetUtils.delete("hello", new String[]{}));
    }

    @Test
    public void testTypicalDelete() {
        assertEquals("eo", CharSetUtils.delete("hello", new String[]{"hl"}));
        assertEquals("ho", CharSetUtils.delete("hello", new String[]{"le"}));
    }

    @Test
    public void testDeleteWithNonExistentChars() {
        assertEquals("hello", CharSetUtils.delete("hello", new String[]{"x", "y"}));
    }

    @Test
    public void testDeleteAllChars() {
        assertEquals("", CharSetUtils.delete("aaa", new String[]{"a"}));
    }

    @Test
    public void testDeleteWithSpecialChars() {
        assertEquals("hell", CharSetUtils.delete("hello!", new String[]{"!"}));
    }
}
