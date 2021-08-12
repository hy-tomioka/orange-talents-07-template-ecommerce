package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Product;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal value;
    private Long quantity;
    private String categoryName;
    private List<NewCharacteristicResponse> characteristics = new ArrayList<>();

    public NewProductResponse(Product product) {
        Assert.isTrue(product != null, "Product must not be null.");
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.value = product.getValue();
        this.quantity = product.getQuantity();
        this.categoryName = product.getCategoryName();
        this.characteristics.addAll(product.getCharacteristics().stream().map(NewCharacteristicResponse::new)
                .collect(Collectors.toSet()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<NewCharacteristicResponse> getCharacteristics() {
        return characteristics;
    }
}
