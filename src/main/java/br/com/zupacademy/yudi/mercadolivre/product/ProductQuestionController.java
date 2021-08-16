package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.email.EmailService;
import br.com.zupacademy.yudi.mercadolivre.product.dto.ProductQuestionRequest;
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
@RequestMapping("/api/products")
public class ProductQuestionController {

    private final ProductRepository productRepository;
    private final EntityManager em;
    private final EmailService mailService;

    public ProductQuestionController(ProductRepository productRepository, EntityManager em, EmailService mailService) {
        this.productRepository = productRepository;
        this.em = em;
        this.mailService = mailService;
    }

    @PostMapping("/{id}/questions")
    @Transactional
    public ResponseEntity<?> send(@RequestBody @Valid ProductQuestionRequest request, @PathVariable("id") Long id,
                                  @AuthenticationPrincipal User user) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProductQuestion question = request.toProductQuestion(user, product);
        em.persist(question);

        mailService.newQuestion(question);

        return ResponseEntity.ok().build();
    }
}
