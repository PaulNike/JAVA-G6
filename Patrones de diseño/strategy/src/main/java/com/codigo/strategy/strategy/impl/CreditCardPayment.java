package com.codigo.strategy.strategy.impl;

import com.codigo.strategy.strategy.PaymentStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    @Override
    public void pay(double amount) {
        log.info("Pagando el monto de: "+amount
        +" con tarjeta de credito: " + cardNumber
        +" Perteneciente a: "+ cardHolderName);
    }
}
