package com.scrap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestDate {


    public static void main(String ... args) throws Exception{



        LocalDate localTime = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");


        String currentTime = localTime.format(formatter);
        System.out.println(currentTime);


        System.out.println("hello");


    }
}
