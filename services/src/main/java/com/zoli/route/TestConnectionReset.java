package com.zoli.route;

import com.zoli.beans.RequestSetter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestConnectionReset extends RouteBuilder {

    @Autowired
    RequestSetter requestSetter;



    @Override
    public void configure() throws Exception {

        HttpComponent myHttp4 = (HttpComponent) getContext().getComponent("http4");

        myHttp4.setConnectionTimeToLive(240000);

        from("timer://mytimer?delay=5s&period=420s&fixedRate=false")
                .log("Testing timer")
                .bean(requestSetter, "setRequest")
                .log("${body}")

                .to("https4://sit-admissions-navitasdemo.studylink.com:443/webservices/providerAPI.cfc")
                .log("${body}")
        ;


        /*from("file:services/src/main/resources/test-data?noop=true&delay=5000")
                .log("${body}");*/

    }
}
