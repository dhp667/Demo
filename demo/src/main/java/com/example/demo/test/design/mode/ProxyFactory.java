package com.example.demo.test.design.mode;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Object getProxyInstance(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("进行代理");
                    method.invoke(target, args);
                    System.out.println("代理完成");
                    return target;
                });
    }


    public static void main(String[] args) {

    }
}

class Charac {
    public void out() {
    }

    ;
}

class A extends Charac {
    String name;

    public void out() {
        System.out.print(name);
    }
}

//@FunctionalInterface
interface C {
    void test3();

    void test4();

    static void test() {
        System.out.println("111");
    }

    static void test1() {
        System.out.println("111");
    }

    static void test2() {
        System.out.println("111");
    }


}
