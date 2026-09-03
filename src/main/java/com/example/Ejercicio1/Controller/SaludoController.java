package com.example.Ejercicio1.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejercicio1.DTO.NumerosDTO;
import com.example.Ejercicio1.Services.SumaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/persona")
@AllArgsConstructor

public class SaludoController {
    public SumaService suma;   
    
    @PostMapping("/enviar")
    public int saludoPost(@RequestBody NumerosDTO numerosDto){
        return suma.sumar(numerosDto);
    }
}