package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CharSetUtils_boolean_containsAny_String_StringArray_Test {

    @Test
    public void testContainsAny_NullString() {
        assertFalse(CharSetUtils.containsAny(null, new String[]{"a", "b"}));
    }

    @Test
    public void testContainsAny_EmptyString() {
        assertFalse(CharSetUtils.containsAny("", new String[]{"a", "b"}));
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
    public void testContainsAny_ValidSetMatch() {
        assertTrue(CharSetUtils.containsAny("hello", new String[]{"k-p"}));
    }

    @Test
    public void testContainsAny_ValidSetNoMatch() {
        assertFalse(CharSetUtils.containsAny("hello", new String[]{"a-d"}));
    }

    @Test
    public void testContainsAny_AllEmptyInputs() {
        assertFalse(CharSetUtils.containsAny("", new String[]{""}));
    }

    @Test
    public void testContainsAny_EmptyElementsInSet() {
        assertTrue(CharSetUtils.containsAny("hello", new String[]{"", "h"}));
    }

    @Test
    public void testContainsAny_SingleCharacterString() {
        assertTrue(CharSetUtils.containsAny("h", new String[]{"h"}));
        assertFalse(CharSetUtils.containsAny("h", new String[]{"a"}));
    }

    @Test
    public void testContainsAny_SingleCharacterSet() {
        assertTrue(CharSetUtils.containsAny("hello", new String[]{"e"}));
        assertFalse(CharSetUtils.containsAny("hello", new String[]{"z"}));
    }

    @Test
    public void testContainsAny_StringWithSpecialCharacters() {
        assertTrue(CharSetUtils.containsAny("h@llo!", new String[]{"@", "!"}));
    }

    @Test
    public void testContainsAny_SetWithSpecialCharacters() {
        assertTrue(CharSetUtils.containsAny("greetings", new String[]{"(", "e)"}));
    }

    @Test
    public void testContainsAny_StringAndSetWithDuplicates() {
        assertTrue(CharSetUtils.containsAny("hellohello", new String[]{"l", "o", "l"}));
    }
}
