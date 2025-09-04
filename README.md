# MP Integration
Este repositório foi feito sobre media de testes avançados para estudos da implementação e integração com a API do Mercado Pago.

## Checklist prático de implementação
- [ ] Criar aplicação no [painel de desenvolvedor Mercado Pago](https://www.mercadopago.com.ar/developers/en/docs/checkout-pro/create-application?) para obter **test** e **prod** access tokens.
- [ ] No projeto Java: adicionar dependência `sdk-java` e inicializar.
- [ ] Implementar front-end com MercadoPago.js / Checkout Brick para tokenizar cartões.
- [ ] Implementar endpoint back-end `/create-payment` que recebe `card_token` e chama `PaymentClient.create(...)` com idempotency key.
- [ ] Configurar endpoint de webhook no painel e implementar verificação de assinatura no backend (HMAC SHA256, confirmar header exato na doc). Enfileirar processamento.
- [ ] Smart Point 2: decidir entre Intent/Deep Link (app móvel) ou flow POS/Server. Implementar Intents conforme `point-android_integration`. Verificar erros comuns (ownership_error, low_battery, etc.).
- [ ] Fazer testes end-to-end no sandbox, depois migrar para produção com tokens de produção.

## Referências para estudo
* Documentação geral Mercado Pago (devs): [visão geral e guias](https://www.mercadopago.com.br/developers).
* SDK Java (repositório oficial; README e exemplos: [`mercadopago/sdk-java` no GitHub](https://github.com/mercadopago/sdk-java).
* Exemplos práticos de pagamento com Checkout/Cartões: [`card-payment-sample-java` no GitHub](https://github.com/mercadopago/card-payment-sample-java).
* MP-Point / Integração com maquininhas: [docs MP-Point](https://www.mercadopago.com.br/developers/en/docs/mp-point/overview).
* Integração Android (Intent / Deep Linking): [repositório `point-android_integration` com exemplos](https://github.com/mercadopago/point-android_integration).
* Notifications / Webhooks (como configurar e validar): docs de
* * [notifications](https://www.mercadopago.com.ar/developers/en/docs/checkout-api-v2/notifications)
  * [webhooks](https://www.mercadopago.com.mx/developers/en/docs/your-integrations/notifications/webhooks).
 
## Observações finais e recomendações práticas
* Comece pelo fluxo mais simples, como tokenização no front + backend com SDK + webhook para confirmar status. Teste exaustivamente no sandbox antes de subir produção. [(Link de apoio)](https://www.mercadopago.com.ar/developers/en/docs/checkout-api/integration-configuration/card/web-integration)
* Para a maquininha Point Smart 2, priorize o uso de *Intent/DeepLink* quando a interação envolver um aplicativo mobile como intermediário; para lojas com PDV robusto, conheça o cadastro de POS/endpoints do MP-Point. [(Link 1 de apoio)](https://www.mercadopago.com.br/developers/en/docs/mp-point/overview) & [Link 2 de apoio](https://github.com/mercadopago/point-android_integration)
* Monitore: registre métricas de sucesso/erro de pagamentos, latência das chamadas à API, e métricas de webhook (retries/falhas). Planeje como lidar com `status_detail` de recusas (fraude, high_risk, etc.). [(Link de apoio)](https://www.reddit.com/r/devsarg/comments/1j4ecza/integraci%C3%B3n_con_mercado_pago/)
