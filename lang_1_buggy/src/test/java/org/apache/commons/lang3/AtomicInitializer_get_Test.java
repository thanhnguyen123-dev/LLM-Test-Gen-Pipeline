package org.apache.commons.lang3;

import org.apache.commons.lang3.concurrent.AtomicInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class AtomicInitializer_get_Test {

    private AtomicInitializer<Object> atomicInitializer;
    private AtomicReference<Object> mockReference;

    @Before
    public void setUp() {
        atomicInitializer = mock(AtomicInitializer.class);
        mockReference = mock(AtomicReference.class);
    }

    @Test
    public void testGet_WithExistingObject() throws ConcurrentException {
        Object expectedObject = new Object();
        when(mockReference.get()).thenReturn(expectedObject);
        when(atomicInitializer.get()).thenCallRealMethod();
        when(atomicInitializer.initialize()).thenReturn(expectedObject);

        Object result = atomicInitializer.get();

        assertEquals(expectedObject, result);
    }

    @Test
    public void testGet_WithNullObject() throws ConcurrentException {
        Object expectedObject = new Object();
        when(mockReference.get()).thenReturn(null).thenReturn(expectedObject);
        when(atomicInitializer.get()).thenCallRealMethod();
        when(atomicInitializer.initialize()).thenReturn(expectedObject);
        when(mockReference.compareAndSet(null, expectedObject)).thenReturn(true);

        Object result = atomicInitializer.get();

        assertEquals(expectedObject, result);
    }

    @Test
    public void testGet_WithConcurrentInitialization() throws ConcurrentException {
        Object initializedObject = new Object();
        Object concurrentObject = new Object();
        when(mockReference.get()).thenReturn(null).thenReturn(concurrentObject);
        when(atomicInitializer.get()).thenCallRealMethod();
        when(atomicInitializer.initialize()).thenReturn(initializedObject);
        when(mockReference.compareAndSet(null, initializedObject)).thenReturn(false);

        Object result = atomicInitializer.get();

        assertEquals(concurrentObject, result);
    }

    @Test(expected = ConcurrentException.class)
    public void testGet_ThrowsConcurrentException() throws ConcurrentException {
        when(mockReference.get()).thenReturn(null);
        when(atomicInitializer.get()).thenCallRealMethod();
        when(atomicInitializer.initialize()).thenThrow(new ConcurrentException());

        atomicInitializer.get();
    }
}
