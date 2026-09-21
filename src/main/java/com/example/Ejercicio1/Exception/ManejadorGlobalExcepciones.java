package com.example.Ejercicio1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Ejercicio1.DTO.ErrorRespuestaDTO;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(DatosAsistenciaInvalidosException.class)
    public ResponseEntity<ErrorRespuestaDTO> manejarDatosInvalidos(
            DatosAsistenciaInvalidosException excepcion) {

        ErrorRespuestaDTO error = new ErrorRespuestaDTO(
                HttpStatus.BAD_REQUEST.value(),
                excepcion.getMessage()
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }
}