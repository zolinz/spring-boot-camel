package com.scrap.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ProcessorWithLocks {

    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void produce () throws InterruptedException{

        synchronized (lock){

            while(true){
                if(list.size() == LIMIT){
                    System.out.println("Waiting for removing items form the list");

                    lock.wait();
                }else{
                    System.out.println("Adding " + value );
                    list.add(value);
                    value++;
                    lock.notify();
                }

                Thread.sleep(2000);
            }
        }
    }

    public void consume() throws InterruptedException{
       // Thread.sleep(1000);
        synchronized (lock){

            while(true){
                if(list.size() == BOTTOM){
                    System.out.println("Waiting for adding items to the list");

                    lock.wait();
                }else{
                    System.out.println("Removed" + list.remove(list.size() -1));
                    lock.notify();
                }

                Thread.sleep(2000);
            }
        }
    }




}
