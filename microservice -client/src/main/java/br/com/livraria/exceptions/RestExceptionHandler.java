package br.com.livraria.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ErrorResponse handleNotFoundException(final ClientNotFoundException clientNotFoundException){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), clientNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientBadRequestException.class)
    public ErrorResponse handleBadRequestException(final ClientBadRequestException clientBadRequestException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), clientBadRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserUnauthorizedException.class)
    public ErrorResponse handlerUnauthorizedException(final UserUnauthorizedException userUnauthorizedException){
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), userUnauthorizedException.getMessage());
    }
}
