package com.zoli.route;

import org.springframework.stereotype.Component;

//@Component
public class MyChildRouteBuilder extends BaseRouteBuilder {


    @Override
    public void configure() throws Exception {
        super.configure();



        from("direct:start")
                .routeId("start-route")
                .log("direct start")
                .to("direct:backend")
                .log("after direct");

/*

        from("direct:backend")
                .id("backend-route")
                .log("backend")
                .throwException(new RuntimeException("hello"))
        ;

*/

    }
}
