package br.com.zupacademy.yudi.mercadolivre.category.dto;

import br.com.zupacademy.yudi.mercadolivre.category.Category;

public class NewCategoryResponse {

    private Long id;
    private String nome;

    public NewCategoryResponse(Category category) {
        this.id = category.getId();
        this.nome = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
