package com.zoli.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class StudyLinkMock extends RouteBuilder {





    public static String readFile(String reference) {
        try {


            InputStream  inputStream = new ClassPathResource(reference).getInputStream();
            String theString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return theString;
            //return org.apache.commons.io.FileUtils.readFileToString(ResourceUtils.getFile("classpath:" + reference), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Can't read file: "+ reference, e);
        }
    }


    @Override
    public void configure() throws Exception {
        getContext().setStreamCaching(true);

        onException(Throwable.class)
                .handled(true)
                .log("gotException")

                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("501"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT, simple("Not implemented"))
                .removeHeader(Exchange.EXCEPTION_CAUGHT)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");

                    }
                })
        ;


        restConfiguration().component("jetty")
                .scheme("http")
                .componentProperty("continuationTimeout", "0")//Request will Never expire from Jetty server. Now It will depend on the client to set the  appropriate socket timeout.
                .bindingMode(RestBindingMode.off)
                .host("0.0.0.0")
                .port("8082")
                .contextPath("/")
        ;


        rest("/webservices/providerAPI.cfc")
                .post()
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");
                    }
                })
                .convertBodyTo(String.class)
                /*.process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");
                    }
                })*/
                .choice()
                    .when().xpath("//*[local-name()='login']")
                       /* .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("hello");
                            }
                        })*/
                        .log("${body}")
                        .setBody(simple(readFile("test-data/LoginResponse.xml")))
                        .log("${body}")

                    .when().xpath("//*[local-name()='updateCourses']")
                        /*.process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("hello");
                            }
                        })*/
                        .log("${body}")
                        .setBody(simple(readFile("test-data/UpdateCourseSuccessResponse.xml")))
                        .log("${body}")

        ;
    }

}
