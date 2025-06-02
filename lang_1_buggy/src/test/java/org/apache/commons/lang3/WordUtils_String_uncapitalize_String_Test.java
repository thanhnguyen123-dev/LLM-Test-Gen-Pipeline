package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class WordUtils_String_uncapitalize_String_Test {

    @Test
    public void testUncapitalize_NullInput() {
        assertNull(WordUtils.uncapitalize(null));
    }

    @Test
    public void testUncapitalize_EmptyString() {
        assertEquals("", WordUtils.uncapitalize(""));
    }

    @Test
    public void testUncapitalize_SingleWord() {
        assertEquals("hello", WordUtils.uncapitalize("Hello"));
    }

    @Test
    public void testUncapitalize_MultipleWords() {
        assertEquals("i am fine", WordUtils.uncapitalize("I Am Fine"));
    }

    @Test
    public void testUncapitalize_MixedCaseWords() {
        assertEquals("i am fINE", WordUtils.uncapitalize("I Am FINE"));
    }

    @Test
    public void testUncapitalize_NoChangeNeeded() {
        assertEquals("i am fine", WordUtils.uncapitalize("i am fine"));
    }
}
