package com.livraria.usermicroservicepag.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public UserErrorResponse handlerNotFoundException(final UserNotFoundException userNotFoundException){
        return new UserErrorResponse(HttpStatus.NOT_FOUND.value(), userNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserBadRequestException.class)
    public UserErrorResponse handlerBadRequestException(final UserBadRequestException userBadRequestException){
        return new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), userBadRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserUnauthorizedException.class)
    public UserErrorResponse handlerUnauthorizedException(final UserUnauthorizedException userUnauthorizedException){
        return new UserErrorResponse(HttpStatus.UNAUTHORIZED.value(), userUnauthorizedException.getMessage());
    }
}
