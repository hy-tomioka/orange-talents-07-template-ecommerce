package br.com.zupacademy.yudi.mercadolivre.purchase;

import br.com.zupacademy.yudi.mercadolivre.email.Email;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static br.com.zupacademy.yudi.mercadolivre.purchase.TransactionStatus.INITIATED;
import static br.com.zupacademy.yudi.mercadolivre.purchase.TransactionStatus.SUCCEEDED;

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
    private TransactionStatus status = INITIATED;

    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;

    @Column(name = "unique_id", nullable = false, length = 16)
    private UUID uniqueId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "costumer_id", nullable = false)
    private User buyer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    private Set<Transaction> transactions = new HashSet<>();

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private Order() {
    }

    public Order(@NotNull @Min(1) Long quantity, @NotBlank String paymentGateway, @NotNull Product product, User buyer) {
        this.quantity = quantity;
        this.product = product;
        this.price = product.getPrice().multiply(new BigDecimal(quantity));
        this.paymentGateway = PaymentGateway.fromValue(paymentGateway);
        this.buyer = buyer;
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

    public void addNewTransaction(Transaction transaction) {
        Assert.isTrue(!transactions.contains(transaction), "Duplicated transaction.");
        Assert.isTrue(!hasSucceededTransaction(), "Order already has a succeeded transaction.");
        transactions.add(transaction);
    }

    public boolean hasSucceededTransaction() {
        return transactions.stream().anyMatch(t -> t.getStatus().equals(SUCCEEDED));
    }

    public Long getBuyerId() {
        return buyer.getId();
    }

    public Long getSellerId() {
        return product.getSellerId();
    }

    public Email formatToEmailSeller() {
        String body = "Product - " + product.getName() + "\nQuantity - " + quantity;
        String subject = "Order from " + buyer.getUsername();
        return new Email(body, subject, "Mercado Livre", "noreply@mercadolivre.com", product.getSellerLogin());
    }

    public Email formatToEmailPostSucceeded() {
        String body = String.format("Product - %s\nQuantity - %s\nPrice - %s\nPaid with - %s",
                product.getName(), String.valueOf(quantity), String.valueOf(price), paymentGateway.getValue());
        String subject = "Order was paid, soon your product will be dispatched.";
        return new Email(body, subject, "Mercado Livre", "noreply@mercadolivre.com", buyer.getUsername());
    }

    public Email formatToEmailPostFailed() {
        String body = String.format("Product - %s\nQuantity - %s\nPrice - %s\nPlease try again.",
                product.getName(), String.valueOf(quantity), String.valueOf(price), paymentGateway.getValue());
        String subject = "Sorry, but your payment process have failed.";
        return new Email(body, subject, "Mercado Livre", "noreply@mercadolivre.com", buyer.getUsername());
    }
}
