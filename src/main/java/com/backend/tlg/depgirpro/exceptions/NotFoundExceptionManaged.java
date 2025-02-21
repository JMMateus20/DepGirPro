package com.backend.tlg.depgirpro.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundExceptionManaged extends BussinessRuleException{

    public NotFoundExceptionManaged(String code, String titulo, String message, HttpStatus httpStatus){
        super (code, titulo, message, httpStatus);
    }

}
