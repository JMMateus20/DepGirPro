package com.backend.tlg.depgirpro.exceptions;

public class InternalServerExceptionManaged extends RuntimeException{

    public InternalServerExceptionManaged(String mensaje){
        super(mensaje);
    }
}
