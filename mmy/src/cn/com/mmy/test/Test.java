package cn.com.mmy.test;

public class Test {
    public static void main( String[] ags){
//        System.out.println("123");
//
//        String a ="b";
//        String b =new String("b").intern();
//        String c = new String("b").intern();
//        System.out.println(c==b);


        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String str1="abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        System.out.println(str1==str2);
        System.out.println(str2==str3);
        System.out.println(str1==str3);

    }
    
}