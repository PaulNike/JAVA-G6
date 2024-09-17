package com.codigo.reactive.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/reactive/v1/")
@Log4j2
public class MonoFluxController {

    @GetMapping("/mono")
    public Mono<String> getMono(){
        //Mono<String> dato = Mono.just("HOla");
        return Mono.just("Hola usuario");
    }

    @GetMapping("/flux")
    public Flux<String> getFlux(@RequestParam("dato")String dato){
        //return Flux.just(dato);
        return Flux.just("Hola","Usuario",dato);
    }

    @GetMapping("/flux-2")
    public Flux<Object> getFlux2(@RequestParam("dato")String dato){
        //return Flux.just(dato);
        return Flux.just("Hola",123,123.10,true,dato);
    }

    @GetMapping("/flux/map")
    public Flux<Integer> getMappedFlux(){
        Flux<Integer> numeros = Flux.just(1,2,3,4,5,6,7,8,9);
        //Flux<Integer> multi = numeros.map( numero -> numero * 2);
        //return multi;
        return numeros.map(num -> num * 2);
    }

    @GetMapping("/flux/filter")
    public Flux<Integer> getFilterFlux(){
        Flux<Integer> numeros = Flux.just(1,2,3,4,5,6,7,8,9);
        //Flux<Integer> numFilter = numeros.filter(numero -> numero % 2 == 0);
        //return numFilter;
        return numeros.filter(num -> num % 2 == 0);
    }

    @GetMapping("/flux/flatmap")
    public Flux<String> getFlatMappedFlux(){
        Flux<String> letras = Flux.just("A","B","C","D");
        //Flux<String> letrasManipuladas = letras.flatMap(letra ->
         //       Flux.just(letra + "1", letra + "2"));
        //return letrasManipuladas;
        return letras.flatMap(letra ->
                Flux.just(letra + "1", letra + "2"));
    }

    @GetMapping("/flux-combinado")
    public Flux<String> getFluxCombinado(){
        Flux<Integer> numeros = Flux.just(1,2,3,4,5,6,7,8,9);
        Flux<String> resultado = numeros
                .filter(numero -> numero % 2 == 0) //Filtre solo pares
                .map(numero -> numero * 3) //Multiplicando todos los pares * 3
                .flatMap( numero -> Flux.just(
                        "Valor Original: "+ numero + " ",
                        "Cuadrado: " + (numero * numero) + " "
                ))
                .doOnNext(numero -> log.info("Procesando dato: "+ numero)); //Log para evidenciar la ejecución
        return resultado;

    }

    @GetMapping("/salvajadaJorge")
    public Mono<List<String>> getMonoList(){
        List<String> nombres = Arrays.asList("Angel","Jorge","Ruben");
        return Mono.just(nombres);
    }


    @GetMapping("/flux-combinado2")
    public Flux<String> getCombinando(){
        Flux<String> letras = Flux.just("A","B","C","D");
        Flux<Integer> numeros = Flux.just(1,2,3,4);
        return letras
                .flatMap(letra -> numeros.map(numero -> letra + numero))
                .filter(filtro -> filtro.endsWith("2")
                        || filtro.endsWith("3")
                        || filtro.endsWith("4"))
                .doOnNext(dato -> log.info("Procesando combinado: " + dato));
    }

    @GetMapping("/flux-error")
    public Flux<String> getFluxCOnError(){
        Flux<Integer> numeros = Flux.just(4,2,0,8,16);
        return numeros.flatMap( numero -> {
            try {
                int resultado = numero / 2;
                return Flux.just("Resultado: " + resultado + " | ");
            }catch (ArithmeticException e){
                return Flux.just("Error: División por 0, valor por defecto: -1");
            }
        }).doOnNext(numero -> log.info("Procesado el numero: " + numero));
    }



}

