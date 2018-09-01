package com.scrap.concurrency;

public class Processor {

    public void produce () throws InterruptedException{

        synchronized (this){
            System.out.println("We are in the producer method");
            wait(20000);
            System.out.println("Again producer methdod");
        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(1000);
        synchronized (this){
            System.out.println("Consumer method");
            Thread.sleep(60000);
            notify();

        }
    }




}
