package cn.itcast.t4.proxy;

public class MyAdvice {
    public Object enhance() {
        System.out.println("before...");

        System.out.println("after...");
        return null;
    }
}
