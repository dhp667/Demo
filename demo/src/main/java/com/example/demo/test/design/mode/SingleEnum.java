package com.example.demo.test.design.mode;

public enum SingleEnum {
    SINGLE_INSTANCE;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SingleEnum getInstance() {
        return SINGLE_INSTANCE;
    }
}
