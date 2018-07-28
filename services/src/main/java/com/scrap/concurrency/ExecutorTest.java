package com.scrap.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTest {



    public static void main(String ... args) throws InterruptedException{

        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());


        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<Integer> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(10000);
            return 5;
        };

        List<Callable<Integer>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);


        Future<Integer> future =
                executorService.submit(callableTask);


        Integer result = null;

        while(!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(1000);
        }

        try {
            result = future.get(15000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        System.exit(0);

    }
}
