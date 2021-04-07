package com.example.android.testing.demo;

import android.arch.lifecycle.Observer;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.annotation.Nullable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author zhangjifeng
 * @date on 2021/04/07 17:36
 * @describe
 */
@RunWith(JUnit4.class)
public class CalculatorViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CalculatorViewModel calculatorViewModel;

    @Before
    public void setUp() {
        calculatorViewModel = new CalculatorViewModel();
    }

    @Test
    public void calcTest() {
        Observer<String> mockObserver = mock(Observer.class);
        calculatorViewModel.calculatorResultLiveData.observeForever(mockObserver);
        verifyNoMoreInteractions(mockObserver);
        calculatorViewModel.calc(1, 2, Calculator.Operator.ADD);
        verify(mockObserver).onChanged("3.0");
    }

    @Test
    public void calcTest_getOrAwaitValue() {
        calculatorViewModel.calc(1, 2, Calculator.Operator.ADD);
        String result = LiveDataTestUtil.getOrAwaitValue(calculatorViewModel.calculatorResultLiveData);
        Assert.assertEquals("3.0", result);
    }
}
