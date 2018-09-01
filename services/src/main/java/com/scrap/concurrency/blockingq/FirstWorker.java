package com.scrap.concurrency.blockingq;

import java.util.concurrent.BlockingQueue;

public class FirstWorker implements Runnable{


    private BlockingQueue<Integer> blockingQueue;


    public FirstWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;

    }


    @Override
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

    }
}
