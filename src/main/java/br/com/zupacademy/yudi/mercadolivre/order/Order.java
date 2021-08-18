package br.com.zupacademy.yudi.mercadolivre.order;

import br.com.zupacademy.yudi.mercadolivre.email.Email;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.user.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.INITIATED;

    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;

    @Column(name = "unique_id", nullable = false)
    private UUID uniqueId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "costumer_id", nullable = false)
    private User buyer;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private Order() {
    }

    public Order(@NotNull @Min(1) Long quantity, @NotBlank String paymentGateway, @NotNull Product product, User buyer) {
        this.quantity = quantity;
        this.product = product;
        this.price = product.getPrice();
        this.paymentGateway = PaymentGateway.fromValue(paymentGateway);
        this.buyer = buyer;
    }

    public Email formatToEmail() {
        String body = "Product - " + product.getName() + "\nQuantity - " + quantity;
        String subject = "Order from " + buyer.getUsername();
        return new Email(body, subject, buyer.getUsername(), buyer.getUsername(), product.getSellerLogin());
    }

    public URI generateGatewayUri(String redirectUrl) {
        return this.paymentGateway.generateUri(this.uniqueId, redirectUrl);
    }

    public Long getId() {
        return id;
    }

    public String getGatewayName() {
        return paymentGateway.getValue();
    }
}
