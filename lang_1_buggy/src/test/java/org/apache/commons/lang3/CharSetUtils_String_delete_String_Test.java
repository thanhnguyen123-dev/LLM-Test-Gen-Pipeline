package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_String_delete_String_Test {

    @Before
    public void setUp() throws Exception {
        // Initialization if needed
    }

    @Test
    public void testDelete_givenNullString_shouldReturnNull() {
        assertNull(CharSetUtils.delete(null, new String[]{"*"}));
    }

    @Test
    public void testDelete_givenEmptyString_shouldReturnEmpty() {
        assertEquals("", CharSetUtils.delete("", new String[]{"*"}));
    }

    @Test
    public void testDelete_givenNullSet_shouldReturnOriginal() {
        assertEquals("hello", CharSetUtils.delete("hello", null));
    }

    @Test
    public void testDelete_givenEmptySet_shouldReturnOriginal() {
        assertEquals("hello", CharSetUtils.delete("hello", new String[]{""}));
    }

    @Test
    public void testDelete_givenSpecificSet_shouldDeleteCharacters() {
        assertEquals("eo", CharSetUtils.delete("hello", new String[]{"hl"}));
        assertEquals("ho", CharSetUtils.delete("hello", new String[]{"le"}));
    }

    @Test
    public void testDelete_givenNoDeletionNeeded_shouldReturnOriginal() {
        assertEquals("hello", CharSetUtils.delete("hello", new String[]{"xyz"}));
    }

    @Test
    public void testDelete_givenAllCharactersToDelete_shouldReturnEmpty() {
        assertEquals("", CharSetUtils.delete("hello", new String[]{"h", "e", "l", "o"}));
    }
}
