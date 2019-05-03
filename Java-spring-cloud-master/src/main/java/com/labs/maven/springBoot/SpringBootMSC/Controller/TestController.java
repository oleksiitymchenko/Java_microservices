package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Messaging.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    Producer publisher;

    @RequestMapping("/send")
    public String sendMessage(){
        String msg = "TESTINGTESINTG";
        System.out.println("*****"+msg);
        for(int i =0; i<25;i++){
            publisher.produceMsg(msg);
        }
        return "Successfully Msg Sent";
    }
}
