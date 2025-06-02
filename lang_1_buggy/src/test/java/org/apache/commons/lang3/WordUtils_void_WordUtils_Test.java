package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WordUtils_void_WordUtils_Test {

    private WordUtils wordUtils;

    @Before
    public void setUp() {
        wordUtils = new WordUtils();
    }

    @Test
    public void testWordUtilsConstructor() {
        // Since WordUtils is not expected to have state and its methods are static,
        // this test will simply ensure no exceptions are thrown during instantiation.
        new WordUtils();
    }
}
