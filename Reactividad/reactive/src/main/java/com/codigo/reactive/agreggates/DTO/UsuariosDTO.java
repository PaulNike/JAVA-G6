package com.codigo.reactive.agreggates.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuariosDTO {
    private String name;
    private String email;
    private String state;
}
