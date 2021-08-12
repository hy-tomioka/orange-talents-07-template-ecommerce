package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Characteristic;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NewCharacteristicRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewCharacteristicRequest that = (NewCharacteristicRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Characteristic toCharacteristic(Product product) {
        Assert.isTrue(product != null, "Product must not be null.");
        return new Characteristic(name, description, product);
    }
}
