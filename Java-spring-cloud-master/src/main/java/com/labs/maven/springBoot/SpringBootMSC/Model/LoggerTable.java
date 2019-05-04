package com.labs.maven.springBoot.SpringBootMSC.Model;

import javax.persistence.*;

@Entity
public class LoggerTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "MessageType")
    private String MessageType;

    @Column(name = "MessageText")
    private String MessageText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }
}
