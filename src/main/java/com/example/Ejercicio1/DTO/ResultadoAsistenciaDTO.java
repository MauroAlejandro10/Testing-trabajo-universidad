package com.example.Ejercicio1.DTO;

import com.example.Ejercicio1.Model.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultadoAsistenciaDTO {

    private final int obligaciones;
    private final int asistencias;
    private final int abonos;
    private final int totalComputado;
    private final double porcentajeMinimo;
    private final double porcentajeSinAbonos;
    private final double porcentajeConAbonos;
    private final EstadoAsistencia estado;
    private final String mensaje;
}