package br.com.zupacademy.yudi.mercadolivre.category.dto;

import br.com.zupacademy.yudi.mercadolivre.category.Category;
import br.com.zupacademy.yudi.mercadolivre.generics.validation.ExistsId;
import br.com.zupacademy.yudi.mercadolivre.generics.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    @ExistsId(domainClass = Category.class)
    private Long superCategoryId;

    public Category toCategory(EntityManager em) {
        if (superCategoryId == null)
            return new Category(name);
        return findAndSetSuperCategory(em);
    }

    private Category findAndSetSuperCategory(EntityManager em) {
        Category superCategory = em.find(Category.class, superCategoryId);
        Category category = new Category(name);
        category.setSuperCategory(superCategory);
        return category;
    }

    public String getName() {
        return name;
    }

    public Long getSuperCategoryId() {
        return superCategoryId;
    }
}
