package com.scrap;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestDate {


    public static void main(String ... args) throws Exception{



        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


        String currentTime = localTime.format(formatter);
        System.out.println(currentTime);


        System.out.println("hello");


    }
}
