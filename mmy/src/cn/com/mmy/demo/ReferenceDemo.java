package cn.com.mmy.demo;

public class ReferenceDemo {
    public static void main(String [] args){
        Demo demo =new Demo();
        System.out.println("demo1=="+demo);
        demo=null;
        System.gc();
        System.out.println("@@@@@@@@@@");
        System.out.println("demo2=="+demo);

    }
}