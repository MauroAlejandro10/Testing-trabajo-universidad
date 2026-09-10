package com.example.Ejercicio1.DTO;

public class SolicitudAsistenciaDTO {

    private int obligaciones;
    private int asistencias;
    private int abonos;
    private double porcentajeMinimo;

    public SolicitudAsistenciaDTO() {
    }

    public SolicitudAsistenciaDTO(
            int obligaciones,
            int asistencias,
            int abonos,
            double porcentajeMinimo) {

        this.obligaciones = obligaciones;
        this.asistencias = asistencias;
        this.abonos = abonos;
        this.porcentajeMinimo = porcentajeMinimo;
    }

    public int getObligaciones() {
        return obligaciones;
    }

    public void setObligaciones(int obligaciones) {
        this.obligaciones = obligaciones;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getAbonos() {
        return abonos;
    }

    public void setAbonos(int abonos) {
        this.abonos = abonos;
    }

    public double getPorcentajeMinimo() {
        return porcentajeMinimo;
    }

    public void setPorcentajeMinimo(double porcentajeMinimo) {
        this.porcentajeMinimo = porcentajeMinimo;
    }
}