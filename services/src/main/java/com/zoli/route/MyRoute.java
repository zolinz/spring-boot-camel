/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.zoli.route;

import com.zoli.beans.KeySetterBean;
import com.zoli.beans.MemoryCrunchBean;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.processor.idempotent.InfinispanIdempotentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MyRoute extends RouteBuilder {


    @Autowired
    InfinispanIdempotentRepository infinispanRepo;

    @Autowired
    KeySetterBean keySetterBean;




    @Override
    public void configure() throws Exception {



        //from("timer://foo1?period=5000")

        from("quartz2://NavigateScheduler/CCSISOTimer?cron=0/10 * * * * ?")

                .id("timer-route1")
            .bean(keySetterBean, "setKey" )
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    System.out.println("hello");
                }
            })

            .setBody().constant("Hello Zoli")
            .idempotentConsumer(simple("${header.key}"), infinispanRepo).eager(true).skipDuplicate(false).removeOnFailure(true)

                .choice()
                    .when(simple("${exchangeProperty.CamelDuplicateMessage} == true"))
                        //.throwException(new RuntimeException("failed"))
                        .log("ROUTE1 DUPLICATE")
                    .otherwise()
                        .log("ROUTE1 SUCCESS")
                .end();



/*

        from("timer://foo2?period=5000")
                .id("timer-route2")
                .bean(keySetterBean, "setKey" )
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");
                    }
                })

                .setBody().constant("Hello Zoli")

                .idempotentConsumer(simple("${header.key}"), infinispanRepo).eager(true).skipDuplicate(false).removeOnFailure(true)

                .choice()
                    .when(simple("${exchangeProperty.CamelDuplicateMessage} == true"))
                        //.throwException(new RuntimeException("failed"))
                        .log("ROUTE2 DUPLICATE")
                    .otherwise()
                        .log("ROUTE2 SUCCESS")
                .end();
*/


                //.log(">>> ${body}");



       /* from("timer://foo3?period=5000")
                .id("timer-route3")
                .bean(keySetterBean, "setKey" )
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");
                    }
                })

                .setBody().constant("Hello Zoli")

                .idempotentConsumer(simple("${header.key}"), infinispanRepo).eager(true).skipDuplicate(false).removeOnFailure(true)

                .choice()
                    .when(simple("${exchangeProperty.CamelDuplicateMessage} == true"))
                        //.throwException(new RuntimeException("failed"))
                        .log("ROUTE3 DUPLICATE")
                    .otherwise()
                        .log("ROUTE3 SUCCESS")
                    .end();
*/

                //.log(">>> ${body}");



    }
}
