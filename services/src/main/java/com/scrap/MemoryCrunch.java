package com.scrap;

import java.math.BigInteger;

public class MemoryCrunch {


    public static void main(String... args)throws Exception{
        int dummyArraySize = 15;
        System.out.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
        BigInteger memoryConsumed = new BigInteger("0");
        try {
            long[] memoryAllocated = null;
            for (int loop = 0; loop < 23; loop++) {
                memoryAllocated = new long[dummyArraySize];
                memoryAllocated[0] = 0;
                memoryConsumed = memoryConsumed.add(BigInteger.valueOf(dummyArraySize * Long.SIZE));
                System.out.println("Memory Consumed till now: " + memoryConsumed  + "   loop" + loop);
                dummyArraySize = dummyArraySize * 2;
                Thread.sleep(1500);
            }
            System.out.println("finished loop");
            Thread.sleep(20000);
        } catch (OutOfMemoryError outofMemory) {
            System.out.println("Catching out of memory error");
            //Log the information,so that we can generate the statistics (latter on).
            throw outofMemory;
        }
    }
}
