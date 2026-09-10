package com.example.Ejercicio1.Exception;

public class DatosAsistenciaInvalidosException extends RuntimeException {

    public DatosAsistenciaInvalidosException(String mensaje) {
        super(mensaje);
    }
}