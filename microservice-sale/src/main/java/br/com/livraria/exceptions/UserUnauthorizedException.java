package br.com.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.ext.Provider;

@Provider
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException{

    public UserUnauthorizedException(String message){
        super(message);
    }
}
