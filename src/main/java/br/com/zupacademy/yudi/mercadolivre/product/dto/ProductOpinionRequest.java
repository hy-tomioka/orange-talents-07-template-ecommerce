package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.product.ProductOpinion;
import br.com.zupacademy.yudi.mercadolivre.user.User;

import javax.validation.constraints.*;

public class ProductOpinionRequest {

    @NotNull
    @Min(1) @Max(5)
    private Integer rating;

    @NotBlank
    private String title;
    @NotBlank @Size(max = 500)
    private String review;

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }

    public ProductOpinion toProductOpinion(Product product, User user) {
        return new ProductOpinion(rating, title, review, product, user);
    }
}
