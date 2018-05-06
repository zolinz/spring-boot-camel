package com.zoli.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class BackendRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        errorHandler(noErrorHandler());

        from("direct:backend")
                .id("backend-route")
                .log("backend")
                .throwException(new RuntimeException("hello"))
        ;

    }
}
