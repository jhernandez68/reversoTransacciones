package com.pruebatecnica.transacciones;

import jakarta.persistence.OptimisticLockException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OptimisticLockException.class, OptimisticLockingFailureException.class})
    public ResponseEntity<String> manejarConcurrencia(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("La transaccion fue modificada concurrentemente");
    }
}
