package cn.com.mmy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态代理
 */
public class ProxyDemo {


    //    public static void main(String[] args) {
//        final List<String> list = new ArrayList<>();
//        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("212222");
//                return method.invoke(list,args);
//
//            }
//        });
//        proxyInstance.add("你好");
//        proxyInstance.add("你好");
//        System.out.println(list);
//        Thread.currentThread().isInterrupted();
//        Thread.interrupted();
//    }
public static void main(String[] args) {
    HashMap<Object, Object> map2 = new HashMap<>(63);
}
}