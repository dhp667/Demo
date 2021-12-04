package com.example.demo;

import java.util.Arrays;
import java.util.Scanner;

public class Test001 {
    private final static String baseUrl = "https://api.binance.com";
    private static final String aa = "12";
    public static void main(String[] a) {
        String main = "1";
        System.out.println("123");

    }


    
    public static void getBuildStore() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入庄家拉盘资金, 用空格分开");
        String bankMoney = scanner.next();
        String[] bankMoneys = bankMoney.split("\t");
        double averageBankMoney = Arrays.stream(bankMoneys).mapToDouble(o->Double.parseDouble(o)).sum();
        System.out.println("输入前五日平均交易额");
        String average = scanner.next();
        System.out.println("建仓成本为: " + (averageBankMoney - 1.5 * Double.parseDouble(average)));


    }



}
class User{
    String a ;
    String d ;
}