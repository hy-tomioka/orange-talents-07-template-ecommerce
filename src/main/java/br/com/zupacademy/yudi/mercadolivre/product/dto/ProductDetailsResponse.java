package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Opinions;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static org.springframework.util.Assert.notNull;

@JsonAutoDetect(fieldVisibility = ANY)
public class ProductDetailsResponse {

    private String name;
    private BigDecimal price;
    private String description;
    private Set<CharacteristicResponse> characteristics = new HashSet<>();
    private Set<String> images = new HashSet<>();
    private List<ProductOpinionResponse> opinions;
    private Double avarageRating;
    private Integer totalRatings;
    private SortedSet<ProductQuestionResponse> questions = new TreeSet<>();

    public ProductDetailsResponse(Product product) {
        notNull(product, "Product must not be null.");

        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();

        this.characteristics.addAll(product.mapCharacteristics(CharacteristicResponse::new));
        this.images.addAll(product.mapImages(image -> image.getLocation()));
        this.questions.addAll(product.mapQuestions(ProductQuestionResponse::new));

        Opinions opinions = product.getOpinions();
        this.opinions = opinions.map(ProductOpinionResponse::new);

        this.avarageRating = opinions.computeAverageRating();
        this.totalRatings = opinions.getSize();

    }
}
