package com.pruebatecnica.transacciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransaccionNoEncontradaException extends RuntimeException {

    public TransaccionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
