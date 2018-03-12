package com.zoli.beans;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Component
public class KeySetterBean {


    public void setKey(Exchange ex){

        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = localTime.format(formatter);


        ex.getIn().setHeader("key", currentTime);


    }
}
