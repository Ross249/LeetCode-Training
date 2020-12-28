package com.train.algorithm.consumerAndProducer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer1 implements Runnable{
    private volatile boolean isRunning = true;
    private BlockingQueue<Integer> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int sleepTime = 1000;

    public Producer1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int data;
        Random random = new Random();
        System.out.println("id="+Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(random.nextInt(sleepTime));
                data = count.incrementAndGet();
                System.out.println("id="+Thread.currentThread().getId()+"data="+data+"size="+queue.size());
                if (!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.err.println("fail:"+data);
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
