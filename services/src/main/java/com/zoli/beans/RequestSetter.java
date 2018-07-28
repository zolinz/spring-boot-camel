package com.zoli.beans;


import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class RequestSetter {

    public void setRequest(Exchange exchange){
        exchange.getIn().setBody("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "                  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "                  xmlns:web=\"http://webservices\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <GetApplicationWithSchema>\n" +
                "            <securityToken xsi:type=\"xsd:string\">DA7E716F-D725-596D-127C159B11E2B55F</securityToken>\n" +
                "            <applicationid xsi:type=\"xsd:string\">156583</applicationid>\n" +
                "            <schemaid xsi:type=\"xsd:string\">A47D9D6C-D9DD-E1B4-9294D100BA5D6FD8</schemaid>\n" +
                "        </GetApplicationWithSchema>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>");
    }
}
