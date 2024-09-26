package com.codigo.reactive.service.impl;

import com.codigo.reactive.agreggates.DTO.UsuariosDTO;
import com.codigo.reactive.agreggates.constants.Constants;
import com.codigo.reactive.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

@Service
@Log4j2
public class KafkaConsumerServiceImpl {

    private final UsuarioService usuarioService;

    public KafkaConsumerServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @KafkaListener(topics = Constants.TOPIC_NAME, groupId = Constants.TOPIC_GROUP)
    public void listenToKafkaTopic(@Payload UsuariosDTO usuariosDTO,
                                   Acknowledgment acknowledgment){
        try {
            usuarioService.saveUser(usuariosDTO)
                    .doOnSuccess( usuarios -> {
                        log.info("Usuario guardado correctamente: " + usuarios.getName());
                        acknowledgment.acknowledge();
                    })
                    .doOnError(error -> {
                        log.error("Error guardando al usuario " + error.getMessage());
                    })
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe(
                            result -> log.info("USUARIO CREADO"),
                            error -> log.error("ERROR GUARDANDO AL USUARIO")
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
