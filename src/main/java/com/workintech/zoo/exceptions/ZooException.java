package com.workintech.zoo.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ZooException extends RuntimeException{
    public HttpStatus httpStatus;
    public ZooException(String message,HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

}
