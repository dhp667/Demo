package com.example.demo.test.react;

public class FunctionTest {
    public static void main(String[] args) {
        Man human = new Man();
        human.show();
    }
}

class Human {
    public String name = "human";

    public void sayHello() {
        System.out.println("大家好,我是" + this.name);
    }
}

class Man extends Human {
    public Man() {
        super.name = "man";
    }


    // 定义方法method,参数传递Greetable接口
    public void method(Greetable g) {
        g.greet();
    }

    public void show() {
        // 调用method方法,使用Lambda表达式
        method(() -> new Human().sayHello());
        method(super::sayHello);

    }
}

@FunctionalInterface
interface Greetable {
    void greet();
}