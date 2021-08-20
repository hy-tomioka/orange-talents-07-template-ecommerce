package br.com.zupacademy.yudi.mercadolivre.purchase;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String gatewayId;

    @CreationTimestamp
    private LocalDateTime instant;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private Transaction() {
    }

    public Transaction(String id, Order order, final TransactionStatus status) {
        this.gatewayId = id;
        this.order = order;
        this.status = status;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public Long getOrderId() {
        return order.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(gatewayId, that.gatewayId) && Objects.equals(instant, that.instant) && Objects.equals(order, that.order) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gatewayId, instant, order, status);
    }
}

