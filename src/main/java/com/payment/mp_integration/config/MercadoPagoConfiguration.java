package com.payment.mp_integration.config;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoConfiguration {
    public MercadoPagoConfiguration(@Value("${mp.access.token}") String accessToken) {
        MercadoPagoConfig.setAccessToken(accessToken);
    }
}
