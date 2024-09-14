package com.codigo.builder.controller;

import com.codigo.builder.model.Cambios;
import com.codigo.builder.model.Carro;
import com.codigo.builder.model.Libro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patron/builder/v1")
public class LibroController {

    @GetMapping("/libronofull")
    public Libro obtenerLibroNormal(){
       //Forma Antigua usando Set de cada atributo
       /* Libro libro = new Libro();
        libro.setTitulo("Paco Yunque");
        libro.setAutor("Cesar Vallejo");
        return libro;*/
        return new Libro.Builder()
                .titulo("Paco Yunque")
                .autor("Cesar Vallejo")
                .build();
    }

    @GetMapping("/libronofull1")
    public Libro obtenerLibroNormal1(){
        return new Libro.Builder()
                .titulo("Paco Yunque")
                .autor("Cesar Vallejo")
                .estado("ACTIVO")
                .build();
    }

    @GetMapping("/carro1")
    public Carro obtenerCarro1(){
        return Carro.builder()
                .marca("MAZDA")
                .tipo("CVT")
                .anio(2021)
                .modelo("CX3")
                .cambios(Cambios.builder()
                        .caja("AUTOMATICA")
                        .numeroCambios(5)
                        .build())
                .build();
    }
}
