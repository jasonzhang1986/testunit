package com.example.android.testing.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author zhangjifeng
 * @date on 2021/04/06 16:29
 * @describe
 */
@RunWith(JUnit4ClassRunner.class)
public class DemoTest{
    @Test
    public void testDoSomething() {
        Demo demo = new Demo();
        assertTrue(demo.doSomething(true));
        assertThat(false, is(equals(demo.doSomething(false))));
        assertThat(false, is(equals(demo.doSomething(null))));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionUseExpected() {
        new ArrayList<Object>().get(0);
    }

    @Test
    public void testExceptionMessage() {
        List<Object> list = new ArrayList<>();

        try {
            list.get(0);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            assertThat(anIndexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
        }
    }

    @Test
    public void testExceptionUseAssertThrows() {
        final List<Object> list = new ArrayList<>();
        IndexOutOfBoundsException thrown = Assert.assertThrows(
                IndexOutOfBoundsException.class,
                new ThrowingRunnable() {
                    @Override
                    public void run() {
                        list.add(1, new Object());
                    }
                });

        // assertions on the thrown exception
        assertEquals("Index: 1, Size: 0", thrown.getMessage());
        // assertions on the state of a domain object after the exception has been thrown
        assertTrue(list.isEmpty());
    }
}