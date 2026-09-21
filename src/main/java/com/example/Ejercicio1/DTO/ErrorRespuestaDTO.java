package com.example.Ejercicio1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorRespuestaDTO {

    private final int estado;
    private final String mensaje;
}