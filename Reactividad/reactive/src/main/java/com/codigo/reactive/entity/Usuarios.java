package com.codigo.reactive.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@Setter
@Builder
public class Usuarios {
    @Id
    private Long id;

    private String name;
    private String email;
    private String state;
}
