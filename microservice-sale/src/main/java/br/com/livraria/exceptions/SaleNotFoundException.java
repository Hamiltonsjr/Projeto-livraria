package br.com.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.ext.Provider;

@Provider
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SaleNotFoundException extends RuntimeException {

    public SaleNotFoundException(String message){
        super(message);
    }
}
