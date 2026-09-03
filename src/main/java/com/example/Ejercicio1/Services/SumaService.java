package com.example.Ejercicio1.Services;

import org.springframework.stereotype.Service;

import com.example.Ejercicio1.DTO.NumerosDTO;

@Service
public class SumaService {
    public int sumar(NumerosDTO numerosDto){
        return numerosDto.getNumero1() + numerosDto.getNumero2();
    }
}