package br.com.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.ext.Provider;

@Provider
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaleBadRequestException extends RuntimeException{

    public SaleBadRequestException(String message){
        super(message);
    }
}
