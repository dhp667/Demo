package cn.com.mmy.demo;

public class Demo {
    public static volatile int money = 10;
    public int id = 1;
    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(Demo.getName());
    }

    public static String getName() {
        String builder = new String("1");
        try {
            throw new Exception();
        } catch (Exception e) {
            return builder;
        } finally {
            builder = "2";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name ;


    public static void set(String name) {
//        Demo demo = new Demo();
//        demo.setName(name);

        Class integer=Integer.TYPE;





    }

}