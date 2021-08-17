package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.email.Email;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "product_questions")
public class ProductQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String question;

    @CreationTimestamp
    private LocalDateTime registryDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Deprecated
    private ProductQuestion() {
    }

    public ProductQuestion(String question, User author, Product product) {
        this.question = question;
        this.author = author;
        this.product = product;
    }

    public Email formatToEmail() {
        return new Email(question, product.getName(), author.getUsername(),
                author.getUsername(), product.getSellerLogin());
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public LocalDateTime getRegistryDate() {
        return registryDate;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuestion that = (ProductQuestion) o;
        return Objects.equals(question, that.question) && Objects.equals(author, that.author) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, author, product);
    }
}
