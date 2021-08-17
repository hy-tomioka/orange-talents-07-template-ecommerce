package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.ProductQuestion;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;
import java.util.Comparator;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class ProductQuestionResponse implements Comparable<ProductQuestionResponse> {

    private Long id;
    private String question;
    private LocalDateTime registryDate;

    public ProductQuestionResponse(ProductQuestion question) {
        this.id = question.getId();
        this.question = question.getQuestion();
        this.registryDate = question.getRegistryDate();
    }

    public LocalDateTime getRegistryDate() {
        return registryDate;
    }

    @Override
    public int compareTo(ProductQuestionResponse o) {
        return Comparator.comparing(ProductQuestionResponse::getRegistryDate).reversed().compare(this, o);
    }
}
