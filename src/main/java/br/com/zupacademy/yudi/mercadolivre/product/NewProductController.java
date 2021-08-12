package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.product.dto.NewProductRequest;
import br.com.zupacademy.yudi.mercadolivre.product.dto.NewProductResponse;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class NewProductController {

    @PersistenceContext
    private EntityManager em;
    private final CharacteristicValidator characteristicValidator;

    public NewProductController(CharacteristicValidator characteristicValidator) {
        this.characteristicValidator = characteristicValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(characteristicValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<NewProductResponse> create(@RequestBody @Valid NewProductRequest request, @AuthenticationPrincipal User user) {

        Product product = request.toProduct(em, user.getId());
        em.persist(product);

        return ResponseEntity.ok(new NewProductResponse(product));
    }
}
