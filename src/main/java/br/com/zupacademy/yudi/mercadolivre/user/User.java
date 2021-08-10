package br.com.zupacademy.yudi.mercadolivre.user;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Email
    @Column(nullable = false)
    private String login;

    @NotBlank @Size(min = 6)
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private User() {
    }

    public User(@NotBlank @Email String login, @NotNull Password password) {
        this.login = login;
        this.password = password.get();
    }
}
