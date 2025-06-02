package org.apache.commons.lang3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.lang3.concurrent.AtomicInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicInitializer_Object_get_Test {

    private AtomicInitializer<Object> initializer;

    @Before
    public void setUp() {
        initializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return "initialized";
            }
        };
    }

    @Test
    public void testGet_TypicalUseCase() {
        try {
            Object result = initializer.get();
            assertEquals("initialized", result);
        } catch (ConcurrentException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testGet_ConcurrentAccess() {
        AtomicReference<Object> reference = new AtomicReference<>(null);
        AtomicInitializer<Object> concurrentInitializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                reference.compareAndSet(null, "concurrent");
                return reference.get();
            }
        };
        try {
            Object result = concurrentInitializer.get();
            assertEquals("concurrent", result);
        } catch (ConcurrentException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testGet_NullInitializeResult() {
        AtomicInitializer<Object> nullInitializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return null;
            }
        };
        try {
            Object result = nullInitializer.get();
            assertNull(result);
        } catch (ConcurrentException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testGet_ExceptionInInitialize() {
        AtomicInitializer<Object> exceptionInitializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() throws ConcurrentException {
                throw new ConcurrentException(new Throwable("Initialization failed"));
            }
        };
        try {
            exceptionInitializer.get();
            fail("Expected ConcurrentException to be thrown.");
        } catch (ConcurrentException e) {
            assertEquals("Initialization failed", e.getCause().getMessage());
        }
    }
}