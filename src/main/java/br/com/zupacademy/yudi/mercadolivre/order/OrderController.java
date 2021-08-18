package br.com.zupacademy.yudi.mercadolivre.order;

import br.com.zupacademy.yudi.mercadolivre.email.EmailService;
import br.com.zupacademy.yudi.mercadolivre.order.dto.OrderRequest;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.product.ProductRepository;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final ProductRepository productRepository;
    private final EmailService mailService;
    private final EntityManager manager;

    public OrderController(ProductRepository productRepository, EmailService mailService, EntityManager manager) {
        this.productRepository = productRepository;
        this.mailService = mailService;
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> process(@RequestBody @Valid OrderRequest request, @AuthenticationPrincipal User user,
                                        UriComponentsBuilder uriComponentsBuilder) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        verifyStock(request.getQuantity(), product);
        Order order = request.toOrder(product, user);
        manager.persist(order);

        mailService.newOrder(order);

        URI uri = buildRedirectUri(uriComponentsBuilder, order);
        return ResponseEntity.status(FOUND).location(uri).build();
    }

    private void verifyStock(Long quantity, Product product) {
        if (!product.hasEnough(quantity)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock quantity is less than required.");
        }
        product.removeFromStock(quantity);
    }

    private URI buildRedirectUri(UriComponentsBuilder uriComponentsBuilder, Order order) {
        String redirectUrl = uriComponentsBuilder.path("/" + order.getGatewayName() + "/{id}")
                .buildAndExpand(order.getId()).toString();
        return order.generateGatewayUri(redirectUrl);
    }
}
