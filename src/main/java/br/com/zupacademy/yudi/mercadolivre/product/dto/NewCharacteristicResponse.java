package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Characteristic;
import org.springframework.util.Assert;

public class NewCharacteristicResponse {

    private Long id;
    private String name;
    private String description;

    public NewCharacteristicResponse(Characteristic characteristic) {
        Assert.isTrue(characteristic != null, "Characteristic must not be null.");
        this.id = characteristic.getId();
        this.name = characteristic.getName();
        this.description = characteristic.getDescription();
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
}
