package com.scrap.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTest {



    public static void main(String ... args) throws Exception{

        ExecutorService executorService =
                new ThreadPoolExecutor(5, 10, 1000L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());


        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "im done";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);


        /*Future<String> future =
                executorService.submit(callableTask);
*/
        List<Future<String>> futures = executorService.invokeAll(callableTasks);


        String result = null;


        Thread.sleep(15000);

        for(Future f : futures){
            result =  (String) f.get(3000, TimeUnit.MILLISECONDS);

            System.out.println(result);
        }

       /* while(!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(2000);
        }

        try {
            result = future.get(35000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
*/


        System.exit(0);

    }
}
