package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.ProductOpinion;

public class ProductOpinionResponse {

    private Long id;
    private Integer rating;
    private String title;
    private String review;

    public ProductOpinionResponse(ProductOpinion opinion) {
        this.id = opinion.getId();
        this.rating = opinion.getRating();
        this.title = opinion.getTitle();
        this.review = opinion.getReview();
    }


    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }
}
