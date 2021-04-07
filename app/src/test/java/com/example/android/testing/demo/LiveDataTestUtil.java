package com.example.android.testing.demo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangjifeng
 * @date on 2021/04/07 17:53
 * @describe
 */
public class LiveDataTestUtil {
    public static long time = 2;
    public static TimeUnit timeUnit = TimeUnit.SECONDS;

    public static<T> T getOrAwaitValue(final LiveData<T> liveData) {
        final T[] data = (T[]) new Object[]{null};
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                data[0] = t;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        try {
            if (latch.getCount() == 1 && latch.await(time, timeUnit)) {
                throw new TimeoutException("LiveData value was never set.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            liveData.removeObserver(observer);
        }
        return data[0];
    }
}
