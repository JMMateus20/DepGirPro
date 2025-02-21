package com.backend.tlg.depgirpro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BussinessRuleException extends RuntimeException{

    private long id;
    private String titulo;
    private String code;
    private HttpStatus httpStatus;

    public BussinessRuleException(String code, String titulo, String message, HttpStatus httpStatus){
        super(message);
        this.code=code;
        this.titulo=titulo;
        this.httpStatus=httpStatus;

    }

    public BussinessRuleException(String message, Throwable cause){
        super(message, cause);
    }





}
