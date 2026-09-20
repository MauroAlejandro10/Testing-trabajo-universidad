package com.example.Ejercicio1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAsistenciaDTO {

    private int obligaciones;
    private int asistencias;
    private int abonos;
    private double porcentajeMinimo;
}