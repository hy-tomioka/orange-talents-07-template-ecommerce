package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.product.ProductQuestion;
import br.com.zupacademy.yudi.mercadolivre.user.User;

import javax.validation.constraints.NotBlank;

public class ProductQuestionRequest {

    @NotBlank
    private String question;

    public String getQuestion() {
        return question;
    }

    public ProductQuestion toProductQuestion(User author, Product product) {
        return new ProductQuestion(question, author, product);
    }
}
