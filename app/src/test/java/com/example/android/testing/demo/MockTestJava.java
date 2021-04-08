package com.example.android.testing.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author zhangjifeng
 * @date on 2021/04/06 11:06
 * @describe
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class MockTestJava {
    @Test
    public void testMockList() {
        List mockList = mock(List.class);
        mockList.add("one");
        mockList.add("two");
        mockList.clear();

        verify(mockList).add("two");
        verify(mockList).clear();
        verify(mockList).add("one");

        //assertEquals(2, mockList.size());  //执行结果是 fail

        InOrder inOrder = Mockito.inOrder(mockList);
        inOrder.verify(mockList).add("one");
        inOrder.verify(mockList).add("two");
        inOrder.verify(mockList).clear();
    }

    @Test
    public void testMock_doCallRealMethod() {
        Demo mockDemo = mock(Demo.class);
        Mockito.when(mockDemo.doSomething(true)).thenReturn(false);
        System.out.println(mockDemo.doSomething(true));
        doCallRealMethod().when(mockDemo).doSomething(true);
        System.out.println("mock doSomething ret=" + mockDemo.doSomething(true));
    }


    @Test
    public void testSpy() {
        Demo spyDemo = spy(new Demo());
        System.out.println(spyDemo.doSomething2());
        doReturn("spy").when(spyDemo).doSomething2();
        System.out.println(spyDemo.doSomething2());
        Mockito.reset(spyDemo);
        when(spyDemo.doSomething2()).thenReturn("spy");
        System.out.println(spyDemo.doSomething2());
    }

    @Test
    public void testSpyList() {
        List<String> list = new ArrayList<>();
        List<String> spyList = spy(list);
        spyList.add("one");
//        doReturn("two").when(spyList).get(0);
        when(spyList.get(0)).thenReturn("two");
       // System.out.println("==="+spyList.get(0));
//        spyList.add("one");
//        spyList.add("two");
//        assertEquals(2, spyList.size());
    }
}
