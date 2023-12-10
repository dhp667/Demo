package com.example.demo.test;

import com.example.demo.test.annotation.MyAnno;
import org.springframework.stereotype.Repository;

@MyAnno(name = "ly")
@Repository
public class Demo01 {
    public Demo01() {
        System.out.print("Demo01 被初始化了");
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    private String usreName;
    private String pwd;
    private int age;

    public static void main(String[] args) {
        Double a = null;
        System.out.print(a == 0.0);
    }
}
