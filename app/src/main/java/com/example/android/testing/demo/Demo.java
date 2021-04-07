package com.example.android.testing.demo;

/**
 * @author zhangjifeng
 * @date on 2021/04/06 16:17
 * @describe demo 类
 */
public class Demo {
    public Boolean doSomething(Boolean param) {
        return param == Boolean.TRUE;
    }

    public String doSomething2() {
        System.out.print("invoked!");
        return "doSomethine2";
    }

}
