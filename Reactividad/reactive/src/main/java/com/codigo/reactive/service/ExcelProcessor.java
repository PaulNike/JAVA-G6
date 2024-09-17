package com.codigo.reactive.service;

import com.codigo.reactive.agreggates.DTO.UsuariosDTO;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;

public interface ExcelProcessor {
    Flux<UsuariosDTO> processExcel(FilePart filePart);
}
