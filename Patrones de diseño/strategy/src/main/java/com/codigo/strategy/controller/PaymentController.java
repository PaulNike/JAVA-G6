package com.codigo.strategy.controller;

import com.codigo.strategy.service.PaymentService;
import com.codigo.strategy.strategy.impl.CreditCardPayment;
import com.codigo.strategy.strategy.impl.CryptoPayment;
import com.codigo.strategy.strategy.impl.PayPalPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment/v1")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit-card")
    public String payWithCreditCard(@RequestParam double amount,
                                    @RequestParam String cardNumber,
                                    @RequestParam String cardHolderNamer){
        paymentService.setPaymentStrategy(new CreditCardPayment(cardNumber,cardHolderNamer));
        paymentService.processPayment(amount);
        return "Pago procesado con Tardeja de Credito";
    }

    @PostMapping("/paypal")
    public String payWithPaypal(@RequestParam double amount,
                                    @RequestParam String email){
        paymentService.setPaymentStrategy(new PayPalPayment(email));
        paymentService.processPayment(amount);
        return "Pago procesado con Paypal Service";
    }

    @PostMapping("/crypto")
    public String payWithCrypto(@RequestParam double amount,
                                @RequestParam String walletAddress){
        paymentService.setPaymentStrategy(new CryptoPayment(walletAddress));
        paymentService.processPayment(amount);
        return "Pago procesado con Crypto Service";
    }
}
