package com.train.algorithm.consumerAndProducer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer1 implements Runnable{
    private BlockingQueue<Integer> queue;
    public static final int sleepTime = 1000;

    public Consumer1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int data;
        Random random = new Random();
        System.out.println("id="+Thread.currentThread().getId());
        try {
            while (true){
                Thread.sleep(random.nextInt(sleepTime));
                if (!queue.isEmpty()){
                    data = queue.take();
                    System.out.println(Thread.currentThread().getId()+"data:"+data+"size:"+queue.size());
                }else {
                    System.out.println("empty"+Thread.currentThread().getId()+"waiting,size="+queue.size());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ExecutorService service = Executors.newCachedThreadPool();
        Producer1 thread1 = new Producer1(queue);
        Consumer1 thread2 = new Consumer1(queue);
        service.execute(thread1);
        service.execute(thread2);
        Thread.sleep(10*1000);
        thread1.stop();
        Thread.sleep(3000);
        service.shutdown();
    }
}
