package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharSetUtils_containsAny_String_StringArray_Test {

    @Test
    public void testContainsAny_NullString() {
        assertFalse(CharSetUtils.containsAny(null, new String[]{"a", "b", "c"}));
    }

    @Test
    public void testContainsAny_EmptyString() {
        assertFalse(CharSetUtils.containsAny("", new String[]{"a", "b", "c"}));
    }

    @Test
    public void testContainsAny_NullSet() {
        assertFalse(CharSetUtils.containsAny("hello", null));
    }

    @Test
    public void testContainsAny_EmptySet() {
        assertFalse(CharSetUtils.containsAny("hello", new String[]{}));
    }

    @Test
    public void testContainsAny_FoundCharacter() {
        assertTrue(CharSetUtils.containsAny("hello", new String[]{"k-p"}));
    }

    @Test
    public void testContainsAny_NotFoundCharacter() {
        assertFalse(CharSetUtils.containsAny("hello", new String[]{"a-d"}));
    }

    @Test
    public void testContainsAny_EmptyBothParameters() {
        assertFalse(CharSetUtils.containsAny("", new String[]{""}));
    }

    @Test
    public void testContainsAny_SingleCharacterSet() {
        assertTrue(CharSetUtils.containsAny("hello", new String[]{"e"}));
    }

    @Test
    public void testContainsAny_SpecialCharacters() {
        assertTrue(CharSetUtils.containsAny("hello!", new String[]{"!"}));
    }
}
