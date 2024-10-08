package com.codigo.ms_other.client;

import com.codigo.ms_other.response.UsuarioResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "ms-seguridad")
public interface SeguridadClient {

    @GetMapping("/api/user/v1/todos")
    List<UsuarioResponseClient>getInfoUsers(@RequestHeader("Authorization") String auth);

}
