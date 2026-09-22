package com.example.Ejercicio1.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.Ejercicio1.DTO.ResultadoAsistenciaDTO;
import com.example.Ejercicio1.DTO.SolicitudAsistenciaDTO;
import com.example.Ejercicio1.Model.EstadoAsistencia;
import com.example.Ejercicio1.Services.CalculoAsistenciaService;
import com.example.Ejercicio1.Services.ValidacionAsistenciaService;

class AsistenciaControllerTest {

    private final ValidacionAsistenciaService validacionService =
            new ValidacionAsistenciaService();

    private final CalculoAsistenciaService calculoService =
            new CalculoAsistenciaService(validacionService);

    private final AsistenciaController controller =
            new AsistenciaController(calculoService);

    @Test
    void deberiaEvaluarSolicitudUsandoServiciosReales() {

        // Arrange
        SolicitudAsistenciaDTO solicitud =
                new SolicitudAsistenciaDTO(20, 8, 2, 50);

        // Act
        ResultadoAsistenciaDTO resultado =
                controller.evaluar(solicitud);

        // Assert
        assertNotNull(resultado);
        assertEquals(10, resultado.getTotalComputado());
        assertEquals(40.0, resultado.getPorcentajeSinAbonos(), 0.001);
        assertEquals(50.0, resultado.getPorcentajeConAbonos(), 0.001);
        assertEquals(
                EstadoAsistencia.CUMPLE_CON_ABONOS,
                resultado.getEstado()
        );
        assertEquals(
                "Cumple gracias a los abonos",
                resultado.getMensaje()
        );
    }
}