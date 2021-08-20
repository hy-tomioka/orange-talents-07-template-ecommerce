package br.com.zupacademy.yudi.mercadolivre.purchase;

import br.com.zupacademy.yudi.mercadolivre.purchase.dto.PaymentGatewayRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class ProcessPaymentOnlyOnce implements Validator {

    private PaymentGatewayRequest request;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentGatewayRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        request = (PaymentGatewayRequest) target;

        if (hasSucceededTransaction(request.getGatewayId())) {
            errors.rejectValue("gatewayId", null,
                    "Transaction already succeeded.");
        }
    }

    private boolean hasSucceededTransaction(String gatewayId) {
        Query query = manager.createQuery(
                "select 1 from Transaction t where t.gatewayId = :gatewayId and t.status = :succeeded");
        query.setParameter("gatewayId", gatewayId);
        query.setParameter("succeeded", TransactionStatus.SUCCEEDED);
        return !query.getResultList().isEmpty();
    }
}
