package com.codigo.reactive.controller;

import com.codigo.reactive.agreggates.DTO.UsuariosDTO;
import com.codigo.reactive.entity.Usuarios;
import com.codigo.reactive.service.ExcelProcessor;
import com.codigo.reactive.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/usuarios/v1")
@Log4j2
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuarioService usuarioService;
    private final ExcelProcessor excelProcessor;

    @PostMapping("/savedOne")
    public Mono<ResponseEntity<Usuarios>> registerUser(@RequestBody UsuariosDTO usuariosDTO){
        return usuarioService.saveUser(usuariosDTO)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @GetMapping(value = "/changes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Usuarios>subscriptionToChanges(){
        return usuarioService.obtainChanges();
    }
}
