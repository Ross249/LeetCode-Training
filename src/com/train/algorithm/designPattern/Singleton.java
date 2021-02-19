package com.train.algorithm.designPattern;

public class Singleton {
    private volatile static Singleton singleton;
    private Singleton(){

    }

    public static Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
