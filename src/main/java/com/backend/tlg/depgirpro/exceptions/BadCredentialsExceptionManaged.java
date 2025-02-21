package com.backend.tlg.depgirpro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

public class BadCredentialsExceptionManaged extends BussinessRuleException {
    public BadCredentialsExceptionManaged(String code, String titulo, String message, HttpStatus httpStatus) {
        super(code, titulo, message, httpStatus);
    }


}
