package com.zoli.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class TimerRouteExample extends RouteBuilder {
    @Override
    public void configure() throws Exception {



        from("timer://mytimer?delay=3s&period=3s&fixedRate=false")
                .log("hello")
        ;

    }
}
