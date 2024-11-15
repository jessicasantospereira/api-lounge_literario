package com.fatec.les.loungeliterarioapi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404() {
        return ResponseEntity.badRequest().body("Recurso não encontrado");
    }
}
