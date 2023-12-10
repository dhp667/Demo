package com.example.demo.test.design.mode;

import java.util.Objects;

public class SingleLazyDoubleCheck {
    private static volatile SingleLazyDoubleCheck instance;

    private SingleLazyDoubleCheck() {
    }

    public static SingleLazyDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingleLazyDoubleCheck.class) {
                if (Objects.isNull(instance)) {
                    instance = new SingleLazyDoubleCheck();
                }
            }
        }
        return instance;
    }
}
