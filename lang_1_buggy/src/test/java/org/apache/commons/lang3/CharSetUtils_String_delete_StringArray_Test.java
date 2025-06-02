package org.apache.commons.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CharSetUtils_String_delete_StringArray_Test {

    @Test
    public void testDelete_NullString() {
        assertNull(CharSetUtils.delete(null, new String[]{"a", "b"}));
    }

    @Test
    public void testDelete_EmptyString() {
        assertEquals("", CharSetUtils.delete("", new String[]{"a", "b"}));
    }

    @Test
    public void testDelete_NullSet() {
        assertEquals("example", CharSetUtils.delete("example", null));
    }

    @Test
    public void testDelete_EmptySet() {
        assertEquals("example", CharSetUtils.delete("example", new String[]{}));
    }

    @Test
    public void testDelete_TypicalCase() {
        assertEquals("eo", CharSetUtils.delete("hello", new String[]{"h", "l"}));
        assertEquals("ho", CharSetUtils.delete("hello", new String[]{"l", "e"}));
    }

    @Test
    public void testDelete_EntireStringDeleted() {
        assertEquals("", CharSetUtils.delete("aaa", new String[]{"a"}));
    }

    @Test
    public void testDelete_NoCharactersDeleted() {
        assertEquals("world", CharSetUtils.delete("world", new String[]{"x", "y", "z"}));
    }

    @Test
    public void testDelete_SingleCharacterInput() {
        assertEquals("", CharSetUtils.delete("a", new String[]{"a"}));
        assertEquals("a", CharSetUtils.delete("a", new String[]{"b"}));
    }

    @Test
    public void testDelete_SpecialCharacters() {
        assertEquals("he11o", CharSetUtils.delete("he@11@o", new String[]{"@"}));
    }
}
