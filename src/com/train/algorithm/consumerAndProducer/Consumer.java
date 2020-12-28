package com.train.algorithm.consumerAndProducer;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Runnable{
    private final Vector sharedQueue;
    private final int size;
    private static  final int sleepTime = 1000;

    public Consumer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.size = size;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("id="+Thread.currentThread().getId());
        try {
            while (true){
                Thread.sleep(random.nextInt(sleepTime));
                while (sharedQueue.isEmpty()){
                    synchronized (sharedQueue){
                        System.out.println("empty" + Thread.currentThread().getId() + "waiting size" + sharedQueue.size());
                        sharedQueue.wait();
                    }
                }
                synchronized (sharedQueue){
                    System.out.println("data:" + sharedQueue.remove(0) + "size" + sharedQueue.size());
                    sharedQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Vector sharedQueue = new Vector();
        int size = 4;
        ExecutorService service = Executors.newCachedThreadPool();
        Producer thread1 = new Producer(sharedQueue,size);
        Consumer thread2 = new Consumer(sharedQueue,size);
        service.execute(thread1);
        service.execute(thread2);
        Thread.sleep(10*1000);
        thread1.stop();
        Thread.sleep(3000);
        service.shutdown();
    }
}
