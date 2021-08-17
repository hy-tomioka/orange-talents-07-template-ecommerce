package br.com.zupacademy.yudi.mercadolivre.product;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL
    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private ProductImage() {
    }

    public ProductImage(String imageLocation, Product product) {
        this.location = imageLocation;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }
}
