package com.example.demo.test.spi;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Constraint {

    String a;
    String b;

    public synchronized void test() {
        a = "11";
        b = "22";
    }

    public void test2() {
        a = "22";
        b = "11";
    }


    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.print("11");
            }
        });


    }

    private void add(List<? super Apple> list) {
        for (int i = 0; i < 10; i++) {
            list.add(new RedApple(i));
        }
    }

    private void get(List<? extends Apple> list) {
        list.forEach(fruit -> {
            System.out.println(fruit.id);
        });
    }

}

class Fruit {
    public Fruit(Integer id) {
        this.id = id;
    }

    public Integer id;

}

class Banner extends Fruit {
    public Banner(Integer id) {
        super(id);
    }
}

class Apple extends Fruit {
    public Apple(Integer id) {
        super(id);
    }
}

class RedApple extends Apple {
    public RedApple(int i) {
        super(i);
    }
}
