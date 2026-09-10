package com.example.Ejercicio1.Services;

import org.springframework.stereotype.Service;

import com.example.Ejercicio1.DTO.SolicitudAsistenciaDTO;
import com.example.Ejercicio1.Exception.DatosAsistenciaInvalidosException;

@Service
public class ValidacionAsistenciaService {

    public void validar(SolicitudAsistenciaDTO solicitud) {

        if (solicitud == null) {
            throw new DatosAsistenciaInvalidosException(
                    "La solicitud no puede ser nula"
            );
        }

        if (solicitud.getObligaciones() <= 0) {
            throw new DatosAsistenciaInvalidosException(
                    "Las obligaciones deben ser mayores que cero"
            );
        }

        if (solicitud.getAsistencias() < 0) {
            throw new DatosAsistenciaInvalidosException(
                    "Las asistencias no pueden ser negativas"
            );
        }

        if (solicitud.getAbonos() < 0) {
            throw new DatosAsistenciaInvalidosException(
                    "Los abonos no pueden ser negativos"
            );
        }

        if (solicitud.getAsistencias() > solicitud.getObligaciones()) {
            throw new DatosAsistenciaInvalidosException(
                    "Las asistencias no pueden superar las obligaciones"
            );
        }

        long totalComputado =
                (long) solicitud.getAsistencias() + solicitud.getAbonos();

        if (totalComputado > solicitud.getObligaciones()) {
            throw new DatosAsistenciaInvalidosException(
                    "La suma de asistencias y abonos no puede superar las obligaciones"
            );
        }

        if (Double.isNaN(solicitud.getPorcentajeMinimo())
                || Double.isInfinite(solicitud.getPorcentajeMinimo())
                || solicitud.getPorcentajeMinimo() < 1
                || solicitud.getPorcentajeMinimo() > 100) {

            throw new DatosAsistenciaInvalidosException(
                    "El porcentaje mínimo debe estar entre 1 y 100"
            );
        }
    }
}