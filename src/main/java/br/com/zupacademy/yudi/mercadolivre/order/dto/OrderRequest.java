package br.com.zupacademy.yudi.mercadolivre.order.dto;

import br.com.zupacademy.yudi.mercadolivre.order.Order;
import br.com.zupacademy.yudi.mercadolivre.product.Product;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class OrderRequest {

    @NotNull @Positive
    private Long productId;
    @NotNull @Min(1)
    private Long quantity;
    @NotBlank
    private String paymentGateway;

    public OrderRequest(Long productId, Long quantity, String paymentGatewayId) {
        this.productId = productId;
        this.quantity = quantity;
        this.paymentGateway = paymentGatewayId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Order toOrder(Product product, User user) {
        return new Order(quantity, paymentGateway, product, user);
    }
}
