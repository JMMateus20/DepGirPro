package com.backend.tlg.depgirpro.exceptions;

import org.springframework.http.HttpStatus;

public class IOExceptionManaged extends BussinessRuleException{

    public IOExceptionManaged(String code, String titulo, String message, HttpStatus httpStatus){
        super (code, titulo, message, httpStatus);
    }

}
