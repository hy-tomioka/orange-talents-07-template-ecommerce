package br.com.zupacademy.yudi.mercadolivre.product.dto;

import br.com.zupacademy.yudi.mercadolivre.category.Category;
import br.com.zupacademy.yudi.mercadolivre.generics.validation.ExistsId;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewProductRequest {

    @NotBlank
    private String name;
    @NotNull @DecimalMin("0.01")
    private BigDecimal price;
    @NotNull @Positive
    private Long quantity;
    @Size(max = 1000)
    private String description;
    @NotNull
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long categoryId;
    @Size(min = 3)
    @Valid
    private List<NewCharacteristicRequest> characteristics;

    public NewProductRequest(String name, BigDecimal price, Long quantity, String description, Long categoryId,
                             List<NewCharacteristicRequest> characteristics) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryId = categoryId;
        this.characteristics = characteristics;
    }

    public List<NewCharacteristicRequest> getCharacteristics() {
        return characteristics;
    }

    public Product toProduct(EntityManager em, Long userId) {
        Assert.isTrue(userId != null, "UserId must not be null.");
        Category category = em.find(Category.class, categoryId);
        User user = em.find(User.class, userId);
        arePresent(category, user);
        return new Product(name, price, quantity, description, category, characteristics, user);
    }

    private void arePresent(Category category, User user) {
        Assert.isTrue(category != null, "Category not found.");
        Assert.isTrue(user != null, "User not found.");
    }

    /**
     * used by spring validator
     * @return
     */
    public boolean hasIdenticalCharacteristicsNames() {
        Set<String> characs = new HashSet<>();
        for (NewCharacteristicRequest characteristic : characteristics) {
            if (!characs.add(characteristic.getName()))
                return true;
        }
        return false;
    }
}
