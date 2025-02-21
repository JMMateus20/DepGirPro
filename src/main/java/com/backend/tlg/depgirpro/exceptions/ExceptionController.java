package com.backend.tlg.depgirpro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundExceptionManaged.class)
    public ResponseEntity<GenericBussinessDTOException> returnNotFoundException(NotFoundExceptionManaged e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.createExceptionMessage(e, "ResponseStatusException"));
    }


    @ExceptionHandler(IOExceptionManaged.class)
    public ResponseEntity<GenericBussinessDTOException> returnIOExceptionManaged(IOExceptionManaged e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.createExceptionMessage(e, "IOException"));
    }

    @ExceptionHandler(InternalServerExceptionManaged.class)
    public ResponseEntity<String> returnInternalServerException(InternalServerExceptionManaged e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsExceptionManaged.class)
    public ResponseEntity<GenericBussinessDTOException> returnBadCredentialsException(BadCredentialsExceptionManaged e){
        return ResponseEntity.badRequest().body(this.createExceptionMessage(e,"BadCredentialsException"));
    }
    @ExceptionHandler(UserDisabledExceptionManaged.class)
    public ResponseEntity<GenericBussinessDTOException> returnDisabledException(UserDisabledExceptionManaged e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(this.createExceptionMessage(e,"UserDisabledException"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedHandlerException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No posee los permisos para realizar esta acción");
    }



    private GenericBussinessDTOException createExceptionMessage(BussinessRuleException e, String tipo){
        return new GenericBussinessDTOException(e.getTitulo(), e.getCode(), e.getMessage(), tipo);
    }




}
