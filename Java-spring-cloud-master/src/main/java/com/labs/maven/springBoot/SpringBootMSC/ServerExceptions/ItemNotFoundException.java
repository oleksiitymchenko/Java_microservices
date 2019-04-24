package com.labs.maven.springBoot.SpringBootMSC.ServerExceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}



