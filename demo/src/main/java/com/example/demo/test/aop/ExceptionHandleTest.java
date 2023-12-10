package com.example.demo.test.aop;

import org.springframework.stereotype.Component;

@Component
public class ExceptionHandleTest {
    public void test() throws StringException {
        throw new StringException();
    }
}
