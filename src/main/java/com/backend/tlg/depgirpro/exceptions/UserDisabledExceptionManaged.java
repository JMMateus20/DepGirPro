package com.backend.tlg.depgirpro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;

public class UserDisabledExceptionManaged extends BussinessRuleException {
    public UserDisabledExceptionManaged(String code, String titulo, String message, HttpStatus httpStatus) {
        super(code, titulo, message, httpStatus);
    }
}
