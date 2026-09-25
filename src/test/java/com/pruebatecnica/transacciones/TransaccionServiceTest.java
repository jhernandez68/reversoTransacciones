package com.pruebatecnica.transacciones;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;



    @Test
    void rechazoTiempo() {
        Transaccion transaccion = new Transaccion();
        transaccion.setEstado(EstadoTransaccion.APROBADA);
        transaccion.setFechaCreacion(LocalDateTime.now().minusHours(25));

        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));

        assertThrows(
                ReversionFueraDePlazoException.class,
                () -> transaccionService.reversar(1L)
        );

        verify(transaccionRepository, never()).saveAndFlush(any());
    }


    
    @Test
    void rechazoEstado() {
        Transaccion transaccion = new Transaccion();
        transaccion.setEstado(EstadoTransaccion.PENDIENTE);
        transaccion.setFechaCreacion(LocalDateTime.now());

        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));

        assertThrows(
                EstadoInvalidoException.class,
                () -> transaccionService.reversar(1L)
        );

        verify(transaccionRepository, never()).saveAndFlush(any());
    }
}
