package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.product.Characteristic;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.util.Assert;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class CharacteristicResponse {

    private Long id;
    private String name;
    private String description;

    public CharacteristicResponse(Characteristic characteristic) {
        Assert.isTrue(characteristic != null, "Characteristic must not be null.");
        this.id = characteristic.getId();
        this.name = characteristic.getName();
        this.description = characteristic.getDescription();
    }
}
