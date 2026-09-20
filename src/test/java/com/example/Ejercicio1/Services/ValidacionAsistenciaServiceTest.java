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
    @Test
void deberiaRechazarSolicitudNula() {

    // Arrange
    SolicitudAsistenciaDTO solicitud = null;

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "La solicitud no puede ser nula",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarAsistenciasNegativas() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, -1, 0, 50);

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "Las asistencias no pueden ser negativas",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarAbonosNegativos() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, 10, -1, 50);

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "Los abonos no pueden ser negativos",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarAsistenciasMayoresQueObligaciones() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, 21, 0, 50);

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "Las asistencias no pueden superar las obligaciones",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarPorcentajeMinimoIgualACero() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, 10, 0, 0);

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "El porcentaje mínimo debe estar entre 1 y 100",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarPorcentajeMinimoMayorQueCien() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(20, 10, 0, 101);

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "El porcentaje mínimo debe estar entre 1 y 100",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarPorcentajeMinimoNaN() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(
                    20,
                    10,
                    0,
                    Double.NaN
            );

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "El porcentaje mínimo debe estar entre 1 y 100",
            excepcion.getMessage()
    );
}
@Test
void deberiaRechazarPorcentajeMinimoInfinito() {

    // Arrange
    SolicitudAsistenciaDTO solicitud =
            new SolicitudAsistenciaDTO(
                    20,
                    10,
                    0,
                    Double.POSITIVE_INFINITY
            );

    // Act
    DatosAsistenciaInvalidosException excepcion =
            assertThrows(
                    DatosAsistenciaInvalidosException.class,
                    () -> servicio.validar(solicitud)
            );

    // Assert
    assertEquals(
            "El porcentaje mínimo debe estar entre 1 y 100",
            excepcion.getMessage()
    );
}
}