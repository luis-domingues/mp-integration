package com.payment.mp_integration.service;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.payment.mp_integration.dto.PaymentRequestDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentClient paymentClient = new PaymentClient();

    public Payment createPayment(PaymentRequestDTO paymentRequestDTO) throws MPApiException, MPException {
        PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                .transactionAmount(BigDecimal.valueOf(paymentRequestDTO.getAmount()))
                .token(paymentRequestDTO.getToken())
                .installments(paymentRequestDTO.getInstallments())
                .paymentMethodId(paymentRequestDTO.getPaymentMethodId())
                .payer(PaymentPayerRequest.builder()
                        .email(paymentRequestDTO.getEmail())
                        .build())
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Idempotency-Key", UUID.randomUUID().toString());
        MPRequestOptions mpRequestOptions = MPRequestOptions.builder()
                .customHeaders(headers)
                .build();
        return paymentClient.create(paymentCreateRequest, mpRequestOptions);
    }
}
