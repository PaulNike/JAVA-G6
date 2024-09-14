package com.codigo.strategy.strategy.impl;

import com.codigo.strategy.strategy.PaymentStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class PayPalPayment implements PaymentStrategy {

    private String email;
    @Override
    public void pay(double amount) {
        log.info("Pagando el monto de: "+amount
                +" con Paypal Service," +
                " Perteneciente al email: " + email);
    }
}
