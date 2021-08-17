package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.ProductOpinion;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
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
}
