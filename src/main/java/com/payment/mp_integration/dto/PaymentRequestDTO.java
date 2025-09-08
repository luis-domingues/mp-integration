package com.payment.mp_integration.dto;

@Data
public class CreatePaymentDTO {
    public String token;
    public String paymentMethodId;
    public String email;
    public String amount;
    public Integer installments = 1;
    public String idempotencyKey;
    public String description;
}
