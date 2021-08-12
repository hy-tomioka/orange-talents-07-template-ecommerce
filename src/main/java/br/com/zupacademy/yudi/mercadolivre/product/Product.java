package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.category.Category;
import br.com.zupacademy.yudi.mercadolivre.product.dto.NewCharacteristicRequest;
import br.com.zupacademy.yudi.mercadolivre.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(nullable = false)
    private Long quantity;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Characteristic> characteristics = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private Product() {
    }

    public Product(String name, BigDecimal value, Long quantity, String description, Category category,
                   Collection<NewCharacteristicRequest> characteristics, User user) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.characteristics.addAll(characteristics.stream().map(c -> c.toCharacteristic(this))
                .collect(Collectors.toSet()));
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public Set<Characteristic> getCharacteristics() {
        return Collections.unmodifiableSet(characteristics);
    }
}
