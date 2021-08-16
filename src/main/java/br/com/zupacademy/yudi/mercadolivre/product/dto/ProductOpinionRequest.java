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

    public ProductOpinionRequest(Integer rating, String title, String review) {
        this.rating = rating;
        this.title = title;
        this.review = review;
    }

    public ProductOpinion toProductOpinion(Product product, User user) {
        return new ProductOpinion(rating, title, review, product, user);
    }
}
