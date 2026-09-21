package com.example.Ejercicio1.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.example.Ejercicio1.DTO.ErrorRespuestaDTO;

class ManejadorGlobalExcepcionesTest {

    private final ManejadorGlobalExcepciones manejador =
            new ManejadorGlobalExcepciones();

    @Test
    void deberiaConvertirExcepcionEnError400() {

        // Arrange
        DatosAsistenciaInvalidosException excepcion =
                new DatosAsistenciaInvalidosException(
                        "Las obligaciones deben ser mayores que cero"
                );

        // Act
        ResponseEntity<ErrorRespuestaDTO> respuesta =
                manejador.manejarDatosInvalidos(excepcion);

        // Assert
        assertEquals(400, respuesta.getStatusCode().value());
        assertNotNull(respuesta.getBody());
        assertEquals(400, respuesta.getBody().getEstado());
        assertEquals(
                "Las obligaciones deben ser mayores que cero",
                respuesta.getBody().getMensaje()
        );
    }
}
