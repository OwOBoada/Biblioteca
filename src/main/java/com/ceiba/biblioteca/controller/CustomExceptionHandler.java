package com.ceiba.biblioteca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TipoUsuarioInvalidoException.class)
    public final ResponseEntity<Object> handleTipoUsuarioInvalidoException(TipoUsuarioInvalidoException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public class CustomErrorResponse {
        private String message;
    
        public CustomErrorResponse(String message, HttpStatus status) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
}
