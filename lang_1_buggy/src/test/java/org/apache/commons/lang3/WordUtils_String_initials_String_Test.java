package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class WordUtils_String_initials_String_Test {

    @Test
    public void testInitialsWithNullString() {
        assertNull(WordUtils.initials(null));
    }

    @Test
    public void testInitialsWithEmptyString() {
        assertEquals("", WordUtils.initials(""));
    }

    @Test
    public void testInitialsWithRegularString() {
        assertEquals("BJL", WordUtils.initials("Ben John Lee"));
    }

    @Test
    public void testInitialsWithPunctuation() {
        assertEquals("BJ", WordUtils.initials("Ben J.Lee"));
    }
}
