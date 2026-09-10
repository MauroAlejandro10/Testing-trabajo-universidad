package com.example.Ejercicio1.Services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.Ejercicio1.DTO.SolicitudAsistenciaDTO;
import com.example.Ejercicio1.Exception.DatosAsistenciaInvalidosException;

class ValidacionAsistenciaServiceTest {

    private final ValidacionAsistenciaService servicio =
            new ValidacionAsistenciaService();

    @Test
    void deberiaAceptarDatosValidos() {

        // Arrange
        SolicitudAsistenciaDTO solicitud =
                new SolicitudAsistenciaDTO(20, 10, 2, 50);

        // Act y Assert
        assertDoesNotThrow(() -> servicio.validar(solicitud));
    }

    @Test
    void deberiaRechazarObligacionesIgualesACero() {

        // Arrange
        SolicitudAsistenciaDTO solicitud =
                new SolicitudAsistenciaDTO(0, 0, 0, 50);

        // Act
        DatosAsistenciaInvalidosException excepcion =
                assertThrows(
                        DatosAsistenciaInvalidosException.class,
                        () -> servicio.validar(solicitud)
                );

        // Assert
        assertEquals(
                "Las obligaciones deben ser mayores que cero",
                excepcion.getMessage()
        );
    }

    @Test
    void deberiaRechazarTotalMayorQueObligaciones() {

        // Arrange
        SolicitudAsistenciaDTO solicitud =
                new SolicitudAsistenciaDTO(20, 18, 3, 50);

        // Act
        DatosAsistenciaInvalidosException excepcion =
                assertThrows(
                        DatosAsistenciaInvalidosException.class,
                        () -> servicio.validar(solicitud)
                );

        // Assert
        assertEquals(
                "La suma de asistencias y abonos no puede superar las obligaciones",
                excepcion.getMessage()
        );
    }
}