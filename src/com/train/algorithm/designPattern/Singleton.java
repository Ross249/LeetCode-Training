package com.train.algorithm.designPattern;

// 双锁检测
public class Singleton {
    private volatile static Singleton singleton;
    private Singleton(){

    }

    public static Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

// 懒汉式
class Singleton1{
    private static volatile Singleton1 singleton1 = null;
    private Singleton1(){}
    public static synchronized Singleton1 getInstance(){
        if (singleton1 == null){
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}

// 饿汉式
class Singleton2{
    private static Singleton2 singleton2 = new Singleton2();
    private Singleton2(){}
    public static Singleton2 getInstance(){
        return singleton2;
    }
}