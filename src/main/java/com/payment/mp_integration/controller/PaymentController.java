package com.payment.mp_integration.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.payment.mp_integration.dto.PaymentRequestDTO;
import com.payment.mp_integration.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        try {
            Payment payment = paymentService.createPayment(paymentRequestDTO);
            return ResponseEntity.ok(payment);
        } catch(MPApiException | MPException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", exception.getMessage()));
        }
    }
}
