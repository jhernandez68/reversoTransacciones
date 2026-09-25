package com.pruebatecnica.transacciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ReversionFueraDePlazoException extends RuntimeException {

    public ReversionFueraDePlazoException(String mensaje) {
        super(mensaje);
    }
}
