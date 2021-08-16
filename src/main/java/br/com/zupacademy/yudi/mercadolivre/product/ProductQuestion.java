package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.email.Email;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_questions")
public class ProductQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "ProductQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", registryDate=" + registryDate +
                ", author=" + author +
                ", product=" + product +
                '}';
    }

    public Email formatToEmail() {
        return new Email(question, product.getName(), author.getUsername(),
                author.getUsername(), product.getSellerLogin());
    }
}
