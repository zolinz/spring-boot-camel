package com.zoli.beans;


import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class MemoryCrunchBean {


    int counter = 10000000;


    public void crunch(Exchange exchange){





        long[] memoryAllocated = new long[10000000];
        memoryAllocated[0] = 0;

        //counter *= 4;
        System.out.println("created large array " );


    }
}
