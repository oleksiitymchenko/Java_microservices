package com.example.eurekaclientlab2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${message.helloworld: Hello default}")
    private String helloMessage;

    @GetMapping(value = "/config")
    public String getHello(){
        return helloMessage;
    }
}
