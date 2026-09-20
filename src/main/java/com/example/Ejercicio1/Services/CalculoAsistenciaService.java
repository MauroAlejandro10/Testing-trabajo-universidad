package com.example.Ejercicio1.Services;

import org.springframework.stereotype.Service;

import com.example.Ejercicio1.DTO.ResultadoAsistenciaDTO;
import com.example.Ejercicio1.DTO.SolicitudAsistenciaDTO;
import com.example.Ejercicio1.Model.EstadoAsistencia;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculoAsistenciaService {

    private final ValidacionAsistenciaService validacionService;

    public ResultadoAsistenciaDTO calcular(
            SolicitudAsistenciaDTO solicitud) {

        validacionService.validar(solicitud);

        int total = solicitud.getAsistencias()
                + solicitud.getAbonos();

        double porcentajeSinAbonos =
                (double) solicitud.getAsistencias()
                / solicitud.getObligaciones() * 100;

        double porcentajeConAbonos =
                (double) total
                / solicitud.getObligaciones() * 100;

        EstadoAsistencia estado;
        String mensaje;

        if (porcentajeSinAbonos
                >= solicitud.getPorcentajeMinimo()) {

            estado = EstadoAsistencia.CUMPLE;
            mensaje = "Cumple mediante asistencias efectivas";

        } else if (porcentajeConAbonos
                >= solicitud.getPorcentajeMinimo()) {

            estado = EstadoAsistencia.CUMPLE_CON_ABONOS;
            mensaje = "Cumple gracias a los abonos";

        } else if (solicitud.getPorcentajeMinimo()
                - porcentajeConAbonos <= 10) {

            estado = EstadoAsistencia.EN_RIESGO;
            mensaje = "No cumple, pero se encuentra en riesgo";

        } else {
            estado = EstadoAsistencia.NO_CUMPLE;
            mensaje = "No cumple el porcentaje mínimo";
        }

        return new ResultadoAsistenciaDTO(
                solicitud.getObligaciones(),
                solicitud.getAsistencias(),
                solicitud.getAbonos(),
                total,
                solicitud.getPorcentajeMinimo(),
                redondear(porcentajeSinAbonos),
                redondear(porcentajeConAbonos),
                estado,
                mensaje
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}