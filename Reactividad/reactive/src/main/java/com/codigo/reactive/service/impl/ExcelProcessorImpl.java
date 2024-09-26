package com.codigo.reactive.service.impl;

import com.codigo.reactive.agreggates.DTO.UsuariosDTO;
import com.codigo.reactive.service.ExcelProcessor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ExcelProcessorImpl implements ExcelProcessor {
    @Override
    public Flux<UsuariosDTO> processExcel(FilePart filePart) {
        return filePart
                .transferTo(Paths.get("temp.xlsx"))
                .thenMany(Flux.create( obj ->{
                    try (Workbook workbook = new XSSFWorkbook(
                            Files.newInputStream(Paths.get("temp.xlsx")))){
                        Sheet sheet = workbook.getSheetAt(0);
                        for (Row row : sheet){
                            UsuariosDTO personDTO = new UsuariosDTO();
                            personDTO.setName(row.getCell(0).getStringCellValue());
                            personDTO.setEmail(row.getCell(1).getStringCellValue());
                            personDTO.setState(row.getCell(2).getStringCellValue());
                            obj.next(personDTO);
                        }
                        obj.complete();
                    }catch (IOException e){
                        obj.error(e);
                    }
                }));
    }
}
