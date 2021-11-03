package br.com.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.ext.Provider;

@Provider
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String message){
        super(message);
    }
}
