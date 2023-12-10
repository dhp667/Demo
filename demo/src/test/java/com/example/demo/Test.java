package com.example.demo;


import lombok.Data;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.lang.System.out;

@ExtendWith(MockitoExtension.class)
public class Test {
    private final static String baseUrl = "https://api.binance.com";
    private static final String aa = "12";
    @Mock
    private User user;

    public static void main(String[] a) {
        String str = null;
        out.print(str.equals("str"));
        if (null != str && str.equals("")) {

        }

    }


    public static void getBuildStore() {
        Scanner scanner = new Scanner(System.in);
        out.println("输入庄家拉盘资金, 用空格分开");
        String bankMoney = scanner.next();
        String[] bankMoneys = bankMoney.split("\t");
        double averageBankMoney = Arrays.stream(bankMoneys).mapToDouble(o -> Double.parseDouble(o)).sum();
        out.println("输入前五日平均交易额");
        String average = scanner.next();
        out.println("建仓成本为: " + (averageBankMoney - 1.5 * Double.parseDouble(average)));


    }

    @org.junit.Test
    void test() {
        School school = new School();
        school.setUser(user);
        school.say();
    }


}

class User {
    String a;
    String d;

    public Boolean out(Supplier<Boolean> supplier) {
        return supplier.get();
    }

    public void out(Runnable runnable) {
        runnable.run();
    }
}

@Data
class School {
    private User user;

    public void say() {
        user.out(() -> true);
    }
}