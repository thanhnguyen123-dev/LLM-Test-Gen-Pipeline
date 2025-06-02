package org.apache.commons.lang3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CharSetUtils_void_CharSetUtils_Test {

    private CharSetUtils charSetUtils;

    @Before
    public void setUp() {
        charSetUtils = new CharSetUtils();
    }

    @Test
    public void testConstructor() {
        // Test if the instance is created successfully
        assert charSetUtils != null;
    }
}
