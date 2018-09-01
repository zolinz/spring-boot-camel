package com.scrap.concurrency.blockingq;

import java.util.concurrent.BlockingQueue;

public class SecondWorker implements Runnable{

    private BlockingQueue<Integer> blockingQueue;


    public SecondWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;

    }


    @Override
    public void run() {

        int counter =0;
        while (true){
            //System.out.println("removing items into the queue");
            try {
               int number =  blockingQueue.take();
                System.out.println("taking item from the queue ..." + number);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
