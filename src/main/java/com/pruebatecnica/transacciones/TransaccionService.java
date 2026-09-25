package com.pruebatecnica.transacciones;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }



    @Transactional
    public void reversar(Long id) {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new TransaccionNoEncontradaException("No encontrada"));

        if (transaccion.getEstado() != EstadoTransaccion.APROBADA) {
            throw new EstadoInvalidoException("solo se pueden reversar APROBADAS");
        }



        LocalDateTime limite = transaccion.getFechaCreacion().plusHours(24);

        if (!LocalDateTime.now().isBefore(limite)) {
            throw new ReversionFueraDePlazoException("Paso mas de 24 hors");
        }


        

        transaccion.setEstado(EstadoTransaccion.REVERSADA);
        transaccionRepository.saveAndFlush(transaccion);
    }
}
