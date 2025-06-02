package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharSetUtils_String_keep_String_Test {

    @Test
    public void testKeep_NullString() {
        assertNull(CharSetUtils.keep(null, new String[]{"a", "b"}));
    }

    @Test
    public void testKeep_NullSet() {
        assertEquals("", CharSetUtils.keep("hello", null));
    }

    @Test
    public void testKeep_EmptyString() {
        assertEquals("", CharSetUtils.keep("", new String[]{"a", "b"}));
    }

    @Test
    public void testKeep_EmptySet() {
        assertEquals("", CharSetUtils.keep("hello", new String[]{}));
    }

    @Test
    public void testKeep_TypicalCase1() {
        assertEquals("hll", CharSetUtils.keep("hello", new String[]{"hl"}));
    }

    @Test
    public void testKeep_TypicalCase2() {
        assertEquals("ell", CharSetUtils.keep("hello", new String[]{"le"}));
    }

    @Test
    public void testKeep_NoMatchingCharacters() {
        assertEquals("", CharSetUtils.keep("hello", new String[]{"x", "y"}));
    }

    @Test
    public void testKeep_AllMatchingCharacters() {
        assertEquals("hello", CharSetUtils.keep("hello", new String[]{"h", "e", "l", "o"}));
    }

    @Test
    public void testKeep_SingleCharacterString() {
        assertEquals("h", CharSetUtils.keep("h", new String[]{"h"}));
        assertEquals("", CharSetUtils.keep("h", new String[]{"a"}));
    }

    @Test
    public void testKeep_SpecialCharacters() {
        assertEquals("!@", CharSetUtils.keep("hello!@", new String[]{"!", "@"}));
    }
}
