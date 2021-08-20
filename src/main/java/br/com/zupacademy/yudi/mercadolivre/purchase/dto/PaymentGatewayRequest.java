package br.com.zupacademy.yudi.mercadolivre.purchase.dto;

import br.com.zupacademy.yudi.mercadolivre.purchase.Order;
import br.com.zupacademy.yudi.mercadolivre.purchase.Transaction;

public interface PaymentGatewayRequest {

    Transaction toTransaction(Order order);

    String getGatewayId();
}
