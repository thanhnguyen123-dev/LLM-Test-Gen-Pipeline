package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_boolean_containsAny_String_StringArray_Test {

    @Test
    public void testContainsAny_NullString() {
        String str = null;
        String[] set = {"a", "b", "c"};
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_EmptyString() {
        String str = "";
        String[] set = {"a", "b", "c"};
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_NullSet() {
        String str = "hello";
        String[] set = null;
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_EmptySet() {
        String str = "hello";
        String[] set = {};
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_TrueCondition() {
        String str = "hello";
        String[] set = {"k-p"};
        assertTrue(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_FalseCondition() {
        String str = "hello";
        String[] set = {"a-d"};
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_SingleCharacterInSet() {
        String str = "abcdef";
        String[] set = {"z"};
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_EmptySetElement() {
        String str = "abcdef";
        String[] set = {""};
        assertFalse(CharSetUtils.containsAny(str, set));
    }

    @Test
    public void testContainsAny_NullElementInSet() {
        String str = "abcdef";
        String[] set = {"a", null};
        assertTrue(CharSetUtils.containsAny(str, set));
    }
}
