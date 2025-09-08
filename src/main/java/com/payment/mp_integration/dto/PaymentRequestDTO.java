package com.payment.mp_integration.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    public String token;
    public String paymentMethodId;
    public String email;
    public Float amount;
    public Integer installments = 1;
}
