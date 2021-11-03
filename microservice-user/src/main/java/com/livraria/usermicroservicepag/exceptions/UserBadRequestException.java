package com.livraria.usermicroservicepag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.ext.Provider;

@Provider
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserBadRequestException extends RuntimeException{

    public UserBadRequestException(String message){
        super(message);
    }
}
