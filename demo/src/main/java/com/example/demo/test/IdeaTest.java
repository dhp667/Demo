package com.example.demo.test;

import sun.misc.Contended;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 @author dhp */
public class IdeaTest {
    @Contended
    static int a;
    @Contended
    static int b;
    int c;
    int d;

    class Temp {
        public void test() {
            System.out.print(a);
            System.out.print(b);
            System.out.print(c);
        }
    }

    public <T> void print(T param) {

    }


    public static void main(String[] args) throws InterruptedException {


    }

    public static class staticC {
        public static String name;
    }

    enum enumA {
        LOCAL,
        REMOTE;
        public String name;

        enumA() {
            this.name = "111";
            System.out.println("实现了构造方法");
        }
    }

    public void test() {
        Thread thread = new Thread(() -> {

        });
        thread.start();
        Temp temp = new Temp();
        temp.test();
    }

    private static void print(int num) {
        for (long i = 1L << 31; i > 0; i = i >> 1) {
            System.out.print((num & i) == 0 ? "0" : "1");
        }
        System.out.println("");
    }

    private static void add(A i) {
        i = new A();
        i.a = 10;
    }

    private static void add2(A i) {
        i.a = 10;
    }

}

class A {
    Integer a;

    public static Object out() {
        return "";
    }

}

class E implements C {
    public String out(IOException b) {
        return "";
    }

}

interface C {
    default void test() {
        System.out.print("");
    }

    ;

    static void test1() {
        System.out.print("11");
    }
}


interface B extends C {
    static void t() {
        System.out.print("1");
    }

    static void t1() {
        System.out.print("1");
    }

    default void t2() {
        System.out.print("1");
    }

    default void t3() {
        System.out.print("1");
    }

    default void t4() {
        this.test();
    }

    @Override
    default void test() {
        C.super.test();
    }
}

interface Reptile {
    ReptileEgg lay() throws Exception;
}


class FireDragon implements Reptile {
    public FireDragon() {
    }

    public static void main(String[] args) throws Exception {
        FireDragon fireDragon = new FireDragon();
        System.out.println(fireDragon instanceof Reptile);
    }

    @Override
    public ReptileEgg lay() throws Exception {
        return new FireDragonEgg(FireDragon::new);
    }
}

class FireDragonEgg extends ReptileEgg {

    public FireDragonEgg(Callable<Reptile> createReptile) throws Exception {
        super(createReptile);
    }
}

class ReptileEgg {

    private boolean isOut = false;
    private Reptile reptile;

    public ReptileEgg(Callable<Reptile> createReptile) throws Exception {
        reptile = createReptile.call();
    }

    public synchronized Reptile hatch() throws Exception {
        if (isOut) {
            throw new IllegalStateException("");
        } else {
            isOut = true;
            return reptile;
        }
    }
}