package com.codigo.reactive.service;

import com.codigo.reactive.agreggates.DTO.UsuariosDTO;
import com.codigo.reactive.entity.Usuarios;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {
    Mono<Usuarios> saveUser(UsuariosDTO usuariosDTO);
    Flux<Usuarios> getAllUsers();
    Flux<Usuarios> obtainChanges();
}
