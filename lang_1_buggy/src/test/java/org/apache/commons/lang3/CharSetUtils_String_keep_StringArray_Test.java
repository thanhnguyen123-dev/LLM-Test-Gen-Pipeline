package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_String_keep_StringArray_Test {

    @Before
    public void setUp() {
        // No need to set up instance as the method is static
    }

    @Test
    public void testKeepWithNullString() {
        String result = CharSetUtils.keep(null, new String[]{"hl"});
        assertNull(result);
    }

    @Test
    public void testKeepWithEmptyString() {
        String result = CharSetUtils.keep("", new String[]{"hl"});
        assertEquals("", result);
    }

    @Test
    public void testKeepWithNullSet() {
        String result = CharSetUtils.keep("hello", null);
        assertEquals("", result);
    }

    @Test
    public void testKeepWithEmptySet() {
        String result = CharSetUtils.keep("hello", new String[]{""});
        assertEquals("", result);
    }

    @Test
    public void testKeepTypicalCase() {
        String result = CharSetUtils.keep("hello", new String[]{"hl"});
        assertEquals("hll", result);
    }

    @Test
    public void testKeepWithAllCharactersToKeep() {
        String result = CharSetUtils.keep("hello", new String[]{"le"});
        assertEquals("ell", result);
    }

    @Test
    public void testKeepWithNoCharactersKept() {
        String result = CharSetUtils.keep("hello", new String[]{"xyz"});
        assertEquals("", result);
    }

    @Test
    public void testKeepWithFullStringKept() {
        String result = CharSetUtils.keep("hello", new String[]{"helo"});
        assertEquals("hello", result);
    }

    @Test
    public void testKeepWithNumbers() {
        String result = CharSetUtils.keep("12345", new String[]{"13"});
        assertEquals("13", result);
    }

    @Test
    public void testKeepWithSpecialCharacters() {
        String result = CharSetUtils.keep("!@#$%", new String[]{"!$"});
        assertEquals("!$", result);
    }
}
