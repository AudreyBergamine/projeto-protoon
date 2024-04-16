package com.proton.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.proton.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//Intercepta as exceções para esta classe efetuar o tratamento
@ControllerAdvice
public class ResourceExceptionHandler {

    //Método para tratar exceções de contéudos não encontrados
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StardandError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";

        HttpStatus status = HttpStatus.NOT_FOUND;
        StardandError err = new StardandError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
