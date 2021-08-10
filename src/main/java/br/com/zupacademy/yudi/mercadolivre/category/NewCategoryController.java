package br.com.zupacademy.yudi.mercadolivre.category;

import br.com.zupacademy.yudi.mercadolivre.category.dto.NewCategoryRequest;
import br.com.zupacademy.yudi.mercadolivre.category.dto.NewCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class NewCategoryController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<NewCategoryResponse> create(@RequestBody @Valid NewCategoryRequest request) {
        Category category = request.toCategory(em);
        em.persist(category);
        return ResponseEntity.ok(new NewCategoryResponse(category));
    }
}
