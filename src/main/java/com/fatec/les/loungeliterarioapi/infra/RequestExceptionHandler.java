package com.fatec.les.loungeliterarioapi.infra;

import com.fatec.les.loungeliterarioapi.dto.ErrorMessage;
import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> threat404() {
        var errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Recurso não encontrado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorMessage> threatDomainException(DomainException e) {
        var errorMessage = new ErrorMessage(e.getStatus().value(), e.getMessage());
        return ResponseEntity.status(e.getError().getStatus()).body(errorMessage);
    }

}
