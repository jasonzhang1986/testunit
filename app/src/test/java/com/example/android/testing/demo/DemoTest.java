package com.example.android.testing.demo;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
}