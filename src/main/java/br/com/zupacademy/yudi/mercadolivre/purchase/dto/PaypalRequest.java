package br.com.zupacademy.yudi.mercadolivre.purchase.dto;

import br.com.zupacademy.yudi.mercadolivre.purchase.Order;
import br.com.zupacademy.yudi.mercadolivre.purchase.TransactionStatus;
import br.com.zupacademy.yudi.mercadolivre.purchase.Transaction;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalRequest implements PaymentGatewayRequest {

    @NotBlank
    private String paymentId;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;

    public PaypalRequest(String paymentId, Integer status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    public Transaction toTransaction(Order order) {
        return new Transaction(paymentId, order, toStatus());
    }

    public TransactionStatus toStatus() {
        if (status == 1)
            return TransactionStatus.SUCCEEDED;
        return TransactionStatus.FAILED;
    }

    @Override
    public String getGatewayId() {
        return paymentId;
    }
}
