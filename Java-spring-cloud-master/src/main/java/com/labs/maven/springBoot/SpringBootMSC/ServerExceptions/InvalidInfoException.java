package com.labs.maven.springBoot.SpringBootMSC.ServerExceptions;

public class InvalidInfoException extends RuntimeException {
    public InvalidInfoException() {
        super();
    }

    public InvalidInfoException(String message) {
        super(message);
    }
}
