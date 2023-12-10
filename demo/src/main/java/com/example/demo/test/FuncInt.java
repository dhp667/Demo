package com.example.demo.test;

public interface FuncInt {
    public abstract void print();

    default void print1() {
        System.out.println("11");
    }

    static void print2() {
        System.out.println("11");
    }

}
