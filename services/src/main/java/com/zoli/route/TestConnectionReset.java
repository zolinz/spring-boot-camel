package com.zoli.route;

import com.zoli.beans.IdleConnectionMonitorThread;
import com.zoli.beans.RequestSetter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class TestConnectionReset extends RouteBuilder {

    @Autowired
    RequestSetter requestSetter;



    @Autowired
    IdleConnectionMonitorThread idleConnectionMonitorThread;


    @Bean
    PoolingHttpClientConnectionManager myPoolingHttpClientConnectionManager() {
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoKeepAlive(false)
                .setSoTimeout(60000)
                //.setSoReuseAddress(true)
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultSocketConfig(socketConfig);
        //connectionManager.closeIdleConnections(5000, TimeUnit.MILLISECONDS);
        return connectionManager;
    }



    @Override
    public void configure() throws Exception {

        HttpComponent myHttp4 = (HttpComponent) getContext().getComponent("http4");

        myHttp4.setConnectionTimeToLive(60000);

        idleConnectionMonitorThread.setHttpClientConnectionManager(myPoolingHttpClientConnectionManager());

        (new Thread(idleConnectionMonitorThread)).start();

        from("timer://mytimer?delay=5s&period=300s&fixedRate=false")
                .log("Testing timer")
                .bean(requestSetter, "setRequest")
                .log("${body}")

                .to("https4://sit-admissions-navitasdemo.studylink.com:443/webservices/providerAPI.cfc?clientConnectionManager=#myPoolingHttpClientConnectionManager")
                .log("${body}")
        ;


        /*from("file:services/src/main/resources/test-data?noop=true&delay=5000")
                .log("${body}");*/

    }
}
