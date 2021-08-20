package br.com.zupacademy.yudi.mercadolivre.external_services;

import br.com.zupacademy.yudi.mercadolivre.purchase.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements SucceededPurchaseEvent {

    public void process(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("orderId", order.getId(), "buyerId", order.getBuyerId());
        restTemplate.postForLocation("http://localhost:8080/external-services/notas-fiscais", request, String.class);
    }
}
