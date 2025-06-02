package org.apache.commons.lang3;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordUtils_capitalizeFully_String_charArray_Test {

    private WordUtils wordUtils;

    @Before
    public void setUp() {
        wordUtils = new WordUtils();
    }

    @Test
    public void testNullString() {
        assertNull(WordUtils.capitalizeFully(null, new char[]{'.'}));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", WordUtils.capitalizeFully("", new char[]{'.'}));
    }

    @Test
    public void testNullDelimiters() {
        assertEquals("Hello World", WordUtils.capitalizeFully("hello world", null));
        assertEquals("Hello", WordUtils.capitalizeFully("hello", null));
    }

    @Test
    public void testEmptyDelimiters() {
        assertEquals("Hello World", WordUtils.capitalizeFully("hello world", new char[0]));
    }

    @Test
    public void testSingleCharacterString() {
        assertEquals("A", WordUtils.capitalizeFully("a", new char[]{'.'}));
    }

    @Test
    public void testTypicalCase() {
        assertEquals("I Am Fine", WordUtils.capitalizeFully("i am fine", new char[]{'.'}));
        assertEquals("I Am.Fine", WordUtils.capitalizeFully("i aM.fine", new char[]{'.'}));
    }

    @Test
    public void testMultipleDelimiters() {
        assertEquals("Hello,World:Here!", WordUtils.capitalizeFully("hello,world:here!", new char[]{',', ':'}));
    }

    @Test
    public void testSpaceDelimiter() {
        assertEquals("John Doe Smith", WordUtils.capitalizeFully("john doe smith", new char[]{' '}));
    }

    @Test
    public void testComplexDelimiter() {
        assertEquals("Hello.World!Here,There:Everywhere", WordUtils.capitalizeFully("hello.world!here,there:everywhere", new char[]{'.', '!', ',', ':'}));
    }
}
