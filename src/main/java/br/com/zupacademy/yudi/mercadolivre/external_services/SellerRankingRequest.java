package br.com.zupacademy.yudi.mercadolivre.external_services;

public class SellerRankingRequest {

    private Long orderId;
    private Long sellerId;

    public Long getOrderId() {
        return orderId;
    }

    public Long getSellerId() {
        return sellerId;
    }
}
