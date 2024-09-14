package com.codigo.builder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Libro {
    private String titulo;
    private String autor;
    private String fechaPublicacion;
    private String editorial;
    private String isbn;
    private String estado;

    private Libro(Builder builder){
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.fechaPublicacion = builder.fechaPublicacion;
        this.editorial = builder.editorial;
        this.isbn = builder.isbn;
        this.estado = builder.estado;

    }
    public static class Builder {
        private String titulo;
        private String autor;
        private String fechaPublicacion;
        private String editorial;
        private String isbn;
        private String estado;

        public Builder titulo(String titulo){
            this.titulo = titulo;
            return this;
        }
        public Builder autor(String autor){
            this.autor = autor;
            return this;
        }
        public Builder fechaPublicacion(String fechaPublicacion){
            this.fechaPublicacion = fechaPublicacion;
            return this;
        }
        public Builder editorial(String editorial){
            this.editorial = editorial;
            return this;
        }
        public Builder isbn(String isbn){
            this.isbn = isbn;
            return this;
        }
        public Builder estado(String estado){
            this.estado = estado;
            return this;
        }
        public Libro build(){
            return new Libro(this);
        }
    }
}
