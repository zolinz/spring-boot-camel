package com.zoli.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


//@Component
public class MyDBRoute extends RouteBuilder {




    @Override
    public void configure() throws Exception {

        from("direct:insert")
                .routeId("generate-order")
                //.bean("orderService", "generateOrder")
                .to("sql:insert into student (id, name) values (4 , 'Rita')?dataSource=#dataSource")
                .log("inserted new Student");



    }
}
