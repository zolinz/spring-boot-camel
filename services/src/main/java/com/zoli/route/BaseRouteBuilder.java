package com.zoli.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


public class BaseRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        onException(Throwable.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");
                    }
                })
                .log("got to onexception");

    }
}
