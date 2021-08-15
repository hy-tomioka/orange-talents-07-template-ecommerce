package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.user.User;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "product_opinions")
public class ProductOpinion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1) @Max(5)
    @Column(nullable = false)
    private Integer rating;
    @NotBlank
    @Column(nullable = false)
    private String title;
    @NotBlank @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String review;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Deprecated
    private ProductOpinion() {
    }

    public ProductOpinion(Integer rating, String title, String review, Product product, User user) {
        this.rating = rating;
        this.title = title;
        this.review = review;
        this.product = product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }
}
