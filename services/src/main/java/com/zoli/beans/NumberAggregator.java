package com.zoli.beans;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NumberAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        int total;

        if (oldExchange == null) {
            total = (Integer) newExchange.getIn().getBody();
            newExchange.setProperty("total", total);
            return newExchange;
        }

        total = (Integer) oldExchange.getProperty("total");

        total = (Integer) newExchange.getIn().getBody() + total;

        oldExchange.setProperty("total", total);

        System.out.println("hello");

        return oldExchange;
    }
}
