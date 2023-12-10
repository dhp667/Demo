package com.example.demo.test;

import org.springframework.stereotype.Component;

@Component
public class AspectTarget {

    public void test() throws Exception {
        System.out.println("执行test");
//        throw new Exception("happen exception");
    }

}
