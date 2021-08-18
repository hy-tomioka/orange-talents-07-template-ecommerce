package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class NewProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private String categoryName;
    private List<CharacteristicResponse> characteristics = new ArrayList<>();

    public NewProductResponse(Product product) {
        Assert.isTrue(product != null, "Product must not be null.");
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getStock();
        this.categoryName = product.getCategoryName();
        this.characteristics.addAll(product.getCharacteristics().stream().map(CharacteristicResponse::new)
                .collect(Collectors.toSet()));
    }
}
