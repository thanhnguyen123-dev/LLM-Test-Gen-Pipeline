package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharSetUtils_String_squeeze_String_Test {

    @Test
    public void testSqueeze_NullString() {
        assertNull(CharSetUtils.squeeze(null, new String[]{"a"}));
    }

    @Test
    public void testSqueeze_EmptyString() {
        assertEquals("", CharSetUtils.squeeze("", new String[]{"a"}));
    }

    @Test
    public void testSqueeze_NullSet() {
        assertEquals("test", CharSetUtils.squeeze("test", null));
    }

    @Test
    public void testSqueeze_EmptySet() {
        assertEquals("test", CharSetUtils.squeeze("test", new String[]{}));
    }

    @Test
    public void testSqueeze_RepeatingCharsInRange() {
        assertEquals("helo", CharSetUtils.squeeze("hello", new String[]{"k-p"}));
    }

    @Test
    public void testSqueeze_NoRepeatingCharsInRange() {
        assertEquals("hello", CharSetUtils.squeeze("hello", new String[]{"a-e"}));
    }

    @Test
    public void testSqueeze_NoAffectedChars() {
        assertEquals("abcdefg", CharSetUtils.squeeze("abcdefg", new String[]{"k-p"}));
    }
}
