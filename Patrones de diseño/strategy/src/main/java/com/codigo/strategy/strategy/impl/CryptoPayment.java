package com.codigo.strategy.strategy.impl;

import com.codigo.strategy.strategy.PaymentStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class CryptoPayment implements PaymentStrategy {

    private String walletAddress;
    @Override
    public void pay(double amount) {
        log.trace("PARA CUANDO QUEIRES HAECR UN SEGUIMIENTO DETALLADO");
        log.info("PARA INFORMAR");
        log.warn("Este es un mensaje de alerta");
        log.debug("este mensaje sale cuand hcemos un debug");
        log.error("solo cuadno queremos informar de un error");
        log.fatal("ERROR TERRIBLE!!!!!");

        log.info("Pagando el monto de: "+amount
                +" con Criptomonedas," +
                " del monedero: " + walletAddress);
        log.info("FINALIZA METODO -- pay -- CryptoPayment");
    }
}
