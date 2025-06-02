package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_uncapitalize_String_Test {

    @Test
    public void testUncapitalize_NullInput() {
        assertNull(WordUtils.uncapitalize(null));
    }

    @Test
    public void testUncapitalize_EmptyString() {
        assertEquals("", WordUtils.uncapitalize(""));
    }

    @Test
    public void testUncapitalize_MixedCasing() {
        assertEquals("i am fINE", WordUtils.uncapitalize("I Am FINE"));
    }

    @Test
    public void testUncapitalize_SingleWord() {
        assertEquals("mixedcase", WordUtils.uncapitalize("MixedCase"));
    }

    @Test
    public void testUncapitalize_AllLowerCase() {
        assertEquals("all lowercase", WordUtils.uncapitalize("all lowercase"));
    }

    @Test
    public void testUncapitalize_NoWhitespace() {
        assertEquals("nospaceshere", WordUtils.uncapitalize("NoSpacesHere"));
    }

    @Test
    public void testUncapitalize_TrailingWhitespace() {
        assertEquals("leading and trailing ", WordUtils.uncapitalize("Leading And Trailing "));
    }

    @Test
    public void testUncapitalize_LeadingWhitespace() {
        assertEquals(" leading and trailing", WordUtils.uncapitalize(" Leading And Trailing"));
    }

    @Test
    public void testUncapitalize_AllUppercase() {
        assertEquals("everything uppercase", WordUtils.uncapitalize("EVERYTHING UPPERCASE"));
    }

    @Test
    public void testUncapitalize_NumbersAndSymbols() {
        assertEquals("123 %chance", WordUtils.uncapitalize("123 %Chance"));
    }
}
