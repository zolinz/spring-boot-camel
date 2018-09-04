package com.scrap.concurrency.delayedblockingq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class DelayedWorker implements Delayed
{


    private BlockingQueue<Integer> blockingQueue;


    public DelayedWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }


    /*@Override
    public void run() {
        int counter =0;
        while (true){
            System.out.println("putting items into the queue " + counter);
            try {
                blockingQueue.put(counter);
                counter++;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }*/
}
