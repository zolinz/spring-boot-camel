package com.zoli.route;

import com.zoli.beans.NumberAggregator;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AggregationRoute extends RouteBuilder {


    @Autowired
    NumberAggregator numberAggregator;

    @Override
    public void configure() throws Exception {
        from("direct:start-aggregation")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        List<Integer> integerList = new ArrayList<>();
                        integerList.add(3);
                        integerList.add(5);
                        integerList.add(6);
                        exchange.getIn().setBody(integerList);
                    }
                })

                .split(simple("body"), numberAggregator)
                    .log("${body}")

                .end()

        ;



    }
}
