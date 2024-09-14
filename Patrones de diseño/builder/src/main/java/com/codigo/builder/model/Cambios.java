package com.codigo.builder.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cambios {
    private String caja;
    private Integer numeroCambios;
}
