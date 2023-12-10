package com.example.demo.test.design.mode;

import java.io.Serializable;

public class SingleStaticInner implements Serializable {
    private SingleStaticInner() {
    }

    private static class SingleStaticInnerInstance {
        public static SingleStaticInner instance = new SingleStaticInner();
    }

    public static SingleStaticInner getInstance() {
        return SingleStaticInnerInstance.instance;
    }


}
