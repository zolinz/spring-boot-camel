package io.fabric8.quickstarts.camel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBeanie {

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }
}
