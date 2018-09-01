package com.scrap.concurrency.blockingq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    public static void main(String ... args){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);



        FirstWorker firstWorker = new FirstWorker(blockingQueue);
        SecondWorker secondWorker = new SecondWorker(blockingQueue);


        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}
