package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.concurrent.AtomicInitializer;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AtomicInitializer_Object_get_Test {

    private AtomicInitializer<Object> atomicInitializer;

    @Before
    public void setUp() {
        atomicInitializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return new Object();
            }
        };
    }

    @Test
    public void testGet_ReturnsInitializedObject() throws Exception {
        assertNotNull(atomicInitializer.get());
    }

    @Test
    public void testGet_SameObjectReturned() throws Exception {
        Object firstCall = atomicInitializer.get();
        Object secondCall = atomicInitializer.get();
        assertSame(firstCall, secondCall);
    }

    @Test
    public void testGet_ConcurrentModification() throws Exception {
        Thread thread = new Thread(() -> {
            try {
                atomicInitializer.get();
            } catch (Exception e) {
                fail("Exception should not be thrown: " + e.getMessage());
            }
        });
        thread.start();
        Object mainThreadResult = atomicInitializer.get();
        thread.join();
        assertNotNull(mainThreadResult);
    }

    @Test
    public void testGet_ReferenceNotInitializedTwice() throws Exception {
        AtomicReference<Object> ref = new AtomicReference<>();
        atomicInitializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                Object obj = new Object();
                if (!ref.compareAndSet(null, obj)) {
                    fail("Initialize called more than once");
                }
                return obj;
            }
        };

        Object result = atomicInitializer.get();
        assertSame(result, ref.get());
    }
}
