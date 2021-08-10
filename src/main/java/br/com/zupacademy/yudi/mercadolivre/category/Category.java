package br.com.zupacademy.yudi.mercadolivre.category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category superCategory;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
