package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){
        ZooErrorResponse error= new ZooErrorResponse(zooException.getHttpStatus().value(),zooException.getMessage(),System.currentTimeMillis());
        log.error(zooException.getMessage());
        return new ResponseEntity<>(error,zooException.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){
        ZooErrorResponse error = new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage(),System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
