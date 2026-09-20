package com.example.Ejercicio1.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.Ejercicio1.DTO.ResultadoAsistenciaDTO;
import com.example.Ejercicio1.DTO.SolicitudAsistenciaDTO;
import com.example.Ejercicio1.Model.EstadoAsistencia;

class CalculoAsistenciaServiceTest {

    private final ValidacionAsistenciaService validacionService =
            new ValidacionAsistenciaService();

    private final CalculoAsistenciaService calculoService =
            new CalculoAsistenciaService(validacionService);

    @Test
    void deberiaCumplirConAsistenciasEfectivas() {

        // Arrange
        SolicitudAsistenciaDTO solicitud =
                new SolicitudAsistenciaDTO(20, 12, 2, 50);

        // Act
        ResultadoAsistenciaDTO resultado =
                calculoService.calcular(solicitud);

        // Assert
        assertNotNull(resultado);
        assertEquals(20, resultado.getObligaciones());
        assertEquals(12, resultado.getAsistencias());
        assertEquals(2, resultado.getAbonos());
        assertEquals(14, resultado.getTotalComputado());

        assertEquals(
                60.0,
                resultado.getPorcentajeSinAbonos(),
                0.001
        );

        assertEquals(
                70.0,
                resultado.getPorcentajeConAbonos(),
                0.001
        );

        assertEquals(
                EstadoAsistencia.CUMPLE,
                resultado.getEstado()
        );

        assertEquals(
                "Cumple mediante asistencias efectivas",
                resultado.getMensaje()
        );
    }

    @Test
    void deberiaCumplirGraciasALosAbonos() {

        // Arrange
        SolicitudAsistenciaDTO solicitud =
                new SolicitudAsistenciaDTO(20, 8, 2, 50);

        // Act
        ResultadoAsistenciaDTO resultado =
                calculoService.calcular(solicitud);

        // Assert
        assertNotNull(resultado);
        assertEquals(20, resultado.getObligaciones());
        assertEquals(8, resultado.getAsistencias());
        assertEquals(2, resultado.getAbonos());
        assertEquals(10, resultado.getTotalComputado());

        assertEquals(
                40.0,
                resultado.getPorcentajeSinAbonos(),
                0.001
        );

        assertEquals(
                50.0,
                resultado.getPorcentajeConAbonos(),
                0.001
        );

        assertEquals(
                EstadoAsistencia.CUMPLE_CON_ABONOS,
                resultado.getEstado()
        );

        assertEquals(
                "Cumple gracias a los abonos",
                resultado.getMensaje()
        );
    }
    @Test
void deberiaQuedarEnRiesgo() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, 8, 0, 50);

    // Act
    ResultadoAsistenciaDTO resultado =
            calculoService.calcular(solicitud);

    // Assert
    assertNotNull(resultado);
    assertEquals(8, resultado.getTotalComputado());

    assertEquals(
            40.0,
            resultado.getPorcentajeSinAbonos(),
            0.001
    );

    assertEquals(
            40.0,
            resultado.getPorcentajeConAbonos(),
            0.001
    );

    assertEquals(
            EstadoAsistencia.EN_RIESGO,
            resultado.getEstado()
    );

    assertEquals(
            "No cumple, pero se encuentra en riesgo",
            resultado.getMensaje()
    );
}
@Test
void deberiaNoCumplir() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, 7, 0, 50);

    // Act
    ResultadoAsistenciaDTO resultado =
            calculoService.calcular(solicitud);

    // Assert
    assertNotNull(resultado);
    assertEquals(7, resultado.getTotalComputado());

    assertEquals(
            35.0,
            resultado.getPorcentajeSinAbonos(),
            0.001
    );

    assertEquals(
            35.0,
            resultado.getPorcentajeConAbonos(),
            0.001
    );

    assertEquals(
            EstadoAsistencia.NO_CUMPLE,
            resultado.getEstado()
    );

    assertEquals(
            "No cumple el porcentaje mínimo",
            resultado.getMensaje()
    );
}
}