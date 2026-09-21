package com.example.Ejercicio1.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejercicio1.DTO.ResultadoAsistenciaDTO;
import com.example.Ejercicio1.DTO.SolicitudAsistenciaDTO;
import com.example.Ejercicio1.Services.CalculoAsistenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {

    private final CalculoAsistenciaService calculoService;

    @PostMapping("/evaluar")
    public ResultadoAsistenciaDTO evaluar(
            @RequestBody SolicitudAsistenciaDTO solicitud) {

        return calculoService.calcular(solicitud);
    }
}