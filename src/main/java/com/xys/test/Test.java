package com.xys.test;

import org.springframework.stereotype.Component;

@Component
public class Test {
    public Test() {}
    public void test() {
        System.out.println("测试");
    }
    public static int test1() {
        return 1;
    }
    public String test2() {
        return "2";
    }
}
