package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Messaging.Producer;
import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    Producer publisher;

    @RequestMapping("/send")
    public String sendMessage(){
        System.out.println("*******************");
        LoggerTable logRecord = new LoggerTable();
        logRecord.setMessageText("TESTCREATAE");
        logRecord.setMessageType("le");
        ObjectMapper mapper = new ObjectMapper();

        //Convert object to JSON string and pretty print
        String jsonInString = null;
        try {
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(logRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonInString);
        publisher.produceMsg("jsa.queue2", jsonInString);
        return "Successfully Msg Sent";
    }
}
