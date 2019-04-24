package com.labs.maven.springBoot.SpringBootMSC.ServerExceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInfoException.class)
    protected ResponseEntity<ExceptionMessage> handleInvalidInfoException(InvalidInfoException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage(),"400"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<ExceptionMessage> handleItemNotFoundException(ItemNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage(),"400"), HttpStatus.BAD_REQUEST);
    }
}
