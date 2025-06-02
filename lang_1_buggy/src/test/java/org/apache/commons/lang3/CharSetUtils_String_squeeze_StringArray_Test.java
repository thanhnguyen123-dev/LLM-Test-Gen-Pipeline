package org.apache.commons.lang3;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.internal.runners.JUnit4ClassRunner;

@RunWith(JUnit4ClassRunner.class)
public class CharSetUtils_String_squeeze_StringArray_Test {

    @Test
    public void testSqueeze_NullString() {
        String result = CharSetUtils.squeeze(null, new String[]{"a-z"});
        Assert.assertNull(result);
    }

    @Test
    public void testSqueeze_EmptyString() {
        String result = CharSetUtils.squeeze("", new String[]{"a-z"});
        Assert.assertEquals("", result);
    }

    @Test
    public void testSqueeze_NullSet() {
        String result = CharSetUtils.squeeze("hello", null);
        Assert.assertEquals("hello", result);
    }

    @Test
    public void testSqueeze_EmptySet() {
        String result = CharSetUtils.squeeze("hello", new String[]{});
        Assert.assertEquals("hello", result);
    }

    @Test
    public void testSqueeze_NoRepetitionsInString() {
        String result = CharSetUtils.squeeze("hello", new String[]{"k-p"});
        Assert.assertEquals("helo", result);
    }

    @Test
    public void testSqueeze_RepetitionsRemainInSetRange() {
        String result = CharSetUtils.squeeze("hello", new String[]{"a-e"});
        Assert.assertEquals("hello", result);
    }

    @Test
    public void testSqueeze_SingleCharacterRepetition() {
        String result = CharSetUtils.squeeze("aaa", new String[]{"a"});
        Assert.assertEquals("a", result);
    }

    @Test
    public void testSqueeze_NonMatchingSet() {
        String result = CharSetUtils.squeeze("hello", new String[]{"x-z"});
        Assert.assertEquals("hello", result);
    }

    @Test
    public void testSqueeze_AllCharactersInSet() {
        String result = CharSetUtils.squeeze("aaabbbccc", new String[]{"a-c"});
        Assert.assertEquals("abc", result);
    }

    @Test
    public void testSqueeze_MixedCharacterUse() {
        String result = CharSetUtils.squeeze("abbcccdddd", new String[]{"b-c", "d"});
        Assert.assertEquals("abbcdd", result);
    }
}
