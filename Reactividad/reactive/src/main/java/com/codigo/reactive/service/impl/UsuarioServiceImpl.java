package com.codigo.reactive.service.impl;

import com.codigo.reactive.agreggates.DTO.UsuariosDTO;
import com.codigo.reactive.entity.Usuarios;
import com.codigo.reactive.repository.UsuariosRepository;
import com.codigo.reactive.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@Log4j2
public class UsuarioServiceImpl implements UsuarioService {

    private final Sinks.Many<Usuarios> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final UsuariosRepository usuariosRepository;

    public UsuarioServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Mono<Usuarios> saveUser(UsuariosDTO usuariosDTO) {
        return usuariosRepository.save(Usuarios.builder()
                .name(usuariosDTO.getName())
                .email(usuariosDTO.getEmail())
                .state(usuariosDTO.getState()).build())
                .doOnSuccess(sink::tryEmitNext)
                .doOnError(error -> {
                    log.info("Error al guardar el usuario", error);
                });
    }

    @Override
    public Flux<Usuarios> getAllUsers() {
        return usuariosRepository.findAll()
                .switchIfEmpty(Flux.error(
                        new RuntimeException("No se encontraron Usuarios")));
    }

    @Override
    public Flux<Usuarios> obtainChanges() {
        return sink.asFlux();
    }
}
