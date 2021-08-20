package br.com.zupacademy.yudi.mercadolivre.external_services;

import br.com.zupacademy.yudi.mercadolivre.purchase.Order;

public interface SucceededPurchaseEvent {

    void process(Order order);
}
