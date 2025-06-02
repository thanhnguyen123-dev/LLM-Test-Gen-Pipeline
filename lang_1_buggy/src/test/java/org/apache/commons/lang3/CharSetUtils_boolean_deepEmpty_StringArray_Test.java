package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_boolean_deepEmpty_StringArray_Test {

    @Test
    public void testDeepEmptyWithNullArray() {
        assertTrue(CharSetUtils.deepEmpty(null));
    }

    @Test
    public void testDeepEmptyWithEmptyArray() {
        assertTrue(CharSetUtils.deepEmpty(new String[]{}));
    }

    @Test
    public void testDeepEmptyWithAllEmptyStrings() {
        assertTrue(CharSetUtils.deepEmpty(new String[]{"", ""}));
    }

    @Test
    public void testDeepEmptyWithNonEmptyString() {
        assertFalse(CharSetUtils.deepEmpty(new String[]{"hello"}));
    }

    @Test
    public void testDeepEmptyWithMixedStrings() {
        assertFalse(CharSetUtils.deepEmpty(new String[]{"", "world"}));
    }
}
