package com.codigo.adapter.controller;

import com.codigo.adapter.adaptador.AdapterBiblioteca;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patron/adapter/v1")
@RequiredArgsConstructor
public class LibroController {
    private final AdapterBiblioteca adapterBiblioteca;

    @GetMapping("/libro/{id}")
    public String obtenerLibro(@PathVariable Integer id){
        return adapterBiblioteca.obtenerDetalleLibro(id);
    }
}
