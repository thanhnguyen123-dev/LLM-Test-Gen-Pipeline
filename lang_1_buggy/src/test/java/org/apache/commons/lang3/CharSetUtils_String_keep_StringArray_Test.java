package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_String_keep_StringArray_Test {

    @Test
    public void testKeepWithNullString() {
        assertNull(CharSetUtils.keep(null, new String[]{"a", "b"}));
    }

    @Test
    public void testKeepWithNullSet() {
        assertEquals("", CharSetUtils.keep("hello", null));
    }

    @Test
    public void testKeepWithEmptyString() {
        assertEquals("", CharSetUtils.keep("", new String[]{"a", "b"}));
    }

    @Test
    public void testKeepWithEmptySet() {
        assertEquals("", CharSetUtils.keep("hello", new String[]{}));
    }

    @Test
    public void testKeepTypicalUseCase() {
        assertEquals("hll", CharSetUtils.keep("hello", new String[]{"hl"}));
    }

    @Test
    public void testKeepAnotherTypicalUseCase() {
        assertEquals("ell", CharSetUtils.keep("hello", new String[]{"le"}));
    }

    @Test
    public void testKeepWithMultipleSets() {
        assertEquals("he", CharSetUtils.keep("hello", new String[]{"he", "xyz"}));
    }

    // Testing edge case with special characters
    @Test
    public void testKeepWithSpecialCharacters() {
        assertEquals("!@#", CharSetUtils.keep("hello!@#", new String[]{"!@", "#"}));
    }
}
