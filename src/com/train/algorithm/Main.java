package com.train.algorithm;

import java.util.concurrent.locks.ReentrantLock;

public class Main implements Runnable{
    public static int i = 1;
    private static ReentrantLock lock = new ReentrantLock(true);

    private int count;
    private String content;

    public Main(int count,String content) {
        this.count = count;
        this.content = content;
    }

    @Override
    public void run() {
        while (count > 0) {
            try {
                lock.lock();
                System.out.println(content);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count--;
                System.out.println(i++);
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Main a = new Main(100,"a");
        Main b = new Main(100,"b");
        Main c = new Main(100,"c");
        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        Thread threadC = new Thread(c);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
