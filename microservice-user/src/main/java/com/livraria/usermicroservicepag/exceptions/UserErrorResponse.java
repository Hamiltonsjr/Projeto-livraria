package com.livraria.usermicroservicepag.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserErrorResponse {

    private final int code;
    private final String message;

}
