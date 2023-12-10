package com.example.demo.test.design.mode;

public class SingleHungry {
    private static final SingleHungry singleHungry = new SingleHungry();

    private SingleHungry() {
    }

    private static SingleHungry getInstance() {
        return singleHungry;
    }
}
