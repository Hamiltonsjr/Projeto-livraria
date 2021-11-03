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
    @ExceptionHandler(SaleNotFoundException.class)
    public ErrorResponse handleNotFoundException(final SaleNotFoundException saleNotFoundException){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), saleNotFoundException.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SaleBadRequestException.class)
    public ErrorResponse handlerBadRequestException(final SaleBadRequestException saleBadRequestException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), saleBadRequestException.getMessage());
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserUnauthorizedException.class)
    public ErrorResponse handlerUnauthorizedException(final UserUnauthorizedException userUnauthorizedException){
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), userUnauthorizedException.getMessage());
    }
}
