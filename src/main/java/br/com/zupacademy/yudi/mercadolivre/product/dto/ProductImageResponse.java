package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.ProductImage;

public class ProductImageResponse {

    private Long id;
    private String resourceLocation;

    public ProductImageResponse(ProductImage image) {
        this.id = image.getId();
        this.resourceLocation = image.getLocation();
    }

    public Long getId() {
        return id;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }
}
