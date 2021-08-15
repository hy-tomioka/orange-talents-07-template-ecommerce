package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.product.dto.ProductOpinionRequest;
import br.com.zupacademy.yudi.mercadolivre.product.dto.ProductOpinionResponse;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductOpinionController {

    private final ProductRepository productRepository;
    private final EntityManager em;

    public ProductOpinionController(ProductRepository productRepository, EntityManager em) {
        this.productRepository = productRepository;
        this.em = em;
    }

    @PostMapping("/{id}/rate")
    @Transactional
    public ResponseEntity<ProductOpinionResponse> rateProduct(@RequestBody @Valid ProductOpinionRequest request, @PathVariable("id") Long id,
                                                              @AuthenticationPrincipal User user) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProductOpinion opinion = request.toProductOpinion(product, user);
        em.persist(opinion);
        return ResponseEntity.ok(new ProductOpinionResponse(opinion));
    }
}
