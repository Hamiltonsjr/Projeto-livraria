package com.livraria.bookstore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(final NotFoundException notFoundException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), notFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(final BadRequestException badRequestException){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),badRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserUnauthorizedException.class)
    public ErrorResponse handlerUnauthorizedException(final UserUnauthorizedException userUnauthorizedException){
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), userUnauthorizedException.getMessage());
    }

}
