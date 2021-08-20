package br.com.zupacademy.yudi.mercadolivre.purchase.dto;

import br.com.zupacademy.yudi.mercadolivre.purchase.Order;
import br.com.zupacademy.yudi.mercadolivre.purchase.Transaction;
import br.com.zupacademy.yudi.mercadolivre.purchase.TransactionStatus;

import javax.validation.constraints.NotBlank;

public class PagseguroRequest implements PaymentGatewayRequest {

    @NotBlank
    private String gatewayId;
    @NotBlank
    private String status;

    public PagseguroRequest(String gatewayId, String status) {
        this.gatewayId = gatewayId;
        this.status = status;
    }

    public Transaction toTransaction(Order order) {
        return new Transaction(gatewayId, order, toStatus());
    }

    private TransactionStatus toStatus() {
        if (status.toUpperCase().equals("SUCESSO")) {
            return TransactionStatus.SUCCEEDED;
        }
        return TransactionStatus.FAILED;
    }

    @Override
    public String getGatewayId() {
        return gatewayId;
    }
}
