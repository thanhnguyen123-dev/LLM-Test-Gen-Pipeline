package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharSetUtils_modify_String_StringArray_boolean_Test {

    @Test
    public void testTypicalUseCase() {
        String input = "hello world";
        String[] set = {"aeiou"};
        boolean expect = true;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("hll wrld", result);
    }

    @Test
    public void testEmptyString() {
        String input = "";
        String[] set = {"aeiou"};
        boolean expect = true;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("", result);
    }

    @Test
    public void testEmptySet() {
        String input = "hello";
        String[] set = {};
        boolean expect = true;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("hello", result);
    }

    @Test
    public void testSingleElementSet() {
        String input = "hello";
        String[] set = {"e"};
        boolean expect = true;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("hllo", result);
    }

    @Test
    public void testNonMatchingExpect() {
        String input = "hello";
        String[] set = {"aeiou"};
        boolean expect = false;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("eo", result);
    }

    @Test
    public void testNullInput() {
        try {
            CharSetUtils.modify(null, new String[]{"aeiou"}, true);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testNullSet() {
        try {
            CharSetUtils.modify("hello", null, true);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testLargeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        String input = sb.toString();
        String[] set = {"a"};
        boolean expect = true;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("", result);
    }

    @Test
    public void testBoundaryCharacters() {
        String input = "abcABC" + Character.MIN_VALUE + Character.MAX_VALUE;
        String[] set = {"ABC"};
        boolean expect = true;
        String result = CharSetUtils.modify(input, set, expect);
        assertEquals("abc" + Character.MIN_VALUE + Character.MAX_VALUE, result);
    }
}
