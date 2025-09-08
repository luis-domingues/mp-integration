package com.payment.mp_integration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    @PostMapping
    public ResponseEntity<?> receiveWebhook(
            @RequestBody Map<String, Object> payload,
            @RequestHeader Map<String, String> headers) {
        System.out.println("payload: " + payload);
        return ResponseEntity.ok().build();
    }
}
