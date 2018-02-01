package com.zoli;

import org.apache.camel.CamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;


//@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisableJmx(true)
public class MyRouteTest {


    @Autowired
    private CamelContext camelContext;


    protected CamelContext createCamelContext() throws Exception { return camelContext; }




    @SpringBootApplication
    @Import(AppConfig.class)
    static class TestConfiguration {
    }




    @Test
    public void restrequestendpoint_whenValidHttpPost_thenMessageGetRoutedToMainRoute() throws Exception {


        final RouteDefinition routePost = camelContext.getRouteDefinition("timer-route");

        camelContext.start();


        camelContext.stop();
    }



}
