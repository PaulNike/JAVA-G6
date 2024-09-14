package com.codigo.builder.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Carro {
    private String modelo;
    private Integer anio;
    private String marca;
    private String tipo;
    private Cambios cambios;
}
