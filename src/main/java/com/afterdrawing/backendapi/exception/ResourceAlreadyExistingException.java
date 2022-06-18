package com.afterdrawing.backendapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceAlreadyExistingException extends RuntimeException{


    public ResourceAlreadyExistingException() {
        super();
    }

    public ResourceAlreadyExistingException(String message) {
        super(message);
    }
}
