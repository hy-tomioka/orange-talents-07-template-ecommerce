package br.com.zupacademy.yudi.mercadolivre.external_services;

import br.com.zupacademy.yudi.mercadolivre.purchase.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements SucceededPurchaseEvent {

    public void process(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("orderId", order.getId(), "sellerId", order.getSellerId());
        restTemplate.postForLocation("http://localhost:8080/external-services/seller-ranking", request, String.class);
    }
}
