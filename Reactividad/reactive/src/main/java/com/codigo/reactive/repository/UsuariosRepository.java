package com.codigo.reactive.repository;

import com.codigo.reactive.entity.Usuarios;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UsuariosRepository extends ReactiveCrudRepository<Usuarios,Long> {
}
