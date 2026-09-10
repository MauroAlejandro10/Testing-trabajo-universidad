package com.example.Ejercicio1.DTO;

import com.example.Ejercicio1.Model.EstadoAsistencia;

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

    public ResultadoAsistenciaDTO(
            int obligaciones,
            int asistencias,
            int abonos,
            int totalComputado,
            double porcentajeMinimo,
            double porcentajeSinAbonos,
            double porcentajeConAbonos,
            EstadoAsistencia estado,
            String mensaje) {

        this.obligaciones = obligaciones;
        this.asistencias = asistencias;
        this.abonos = abonos;
        this.totalComputado = totalComputado;
        this.porcentajeMinimo = porcentajeMinimo;
        this.porcentajeSinAbonos = porcentajeSinAbonos;
        this.porcentajeConAbonos = porcentajeConAbonos;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public int getObligaciones() {
        return obligaciones;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getAbonos() {
        return abonos;
    }

    public int getTotalComputado() {
        return totalComputado;
    }

    public double getPorcentajeMinimo() {
        return porcentajeMinimo;
    }

    public double getPorcentajeSinAbonos() {
        return porcentajeSinAbonos;
    }

    public double getPorcentajeConAbonos() {
        return porcentajeConAbonos;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }
}