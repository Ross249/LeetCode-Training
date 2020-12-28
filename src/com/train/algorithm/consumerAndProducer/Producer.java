package com.train.algorithm.consumerAndProducer;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    private volatile boolean isRunning = true;
    private final Vector sharedQueue;
    private final int size;
    private static AtomicInteger count = new AtomicInteger();
    private static final int sleepTime = 1000;

    public Producer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.size = size;
    }

    @Override
    public void run() {
        int data;
        Random random = new Random();
        System.out.println("start producer id="+Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(random.nextInt(sleepTime));
                while (sharedQueue.size()==size){
                    synchronized (sharedQueue){
                        System.out.println("full"+Thread.currentThread().getId()+"waiting,size"+sharedQueue.size());
                        sharedQueue.wait();
                    }
                }
                synchronized (sharedQueue){
                    data = count.incrementAndGet();
                    sharedQueue.add(data);
                    sharedQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public void stop(){
        isRunning = false;
    }
}
