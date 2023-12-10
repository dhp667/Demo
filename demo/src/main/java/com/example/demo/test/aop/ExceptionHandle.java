package com.example.demo.test.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandle {
    @Autowired
    ExceptionHandleTest test;

    public void test() throws StringException {
        test.test();
    }
}
