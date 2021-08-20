package br.com.zupacademy.yudi.mercadolivre.purchase;

import br.com.zupacademy.yudi.mercadolivre.email.EmailService;
import br.com.zupacademy.yudi.mercadolivre.external_services.SucceededPurchaseEvent;
import br.com.zupacademy.yudi.mercadolivre.purchase.dto.PagseguroRequest;
import br.com.zupacademy.yudi.mercadolivre.purchase.dto.PaymentGatewayRequest;
import br.com.zupacademy.yudi.mercadolivre.purchase.dto.PaypalRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class PaymentController {

    private final OrderRepository orderRepository;
    private final ProcessPaymentOnlyOnce processPaymentOnlyOnce;
    private final EmailService emailService;
    private final Set<SucceededPurchaseEvent> succeededEvents;

    public PaymentController(OrderRepository orderRepository, ProcessPaymentOnlyOnce processPaymentOnlyOnce,
                             EmailService emailService, Set<SucceededPurchaseEvent> succeededEvents) {
        this.orderRepository = orderRepository;
        this.processPaymentOnlyOnce = processPaymentOnlyOnce;
        this.emailService = emailService;
        this.succeededEvents = succeededEvents;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(processPaymentOnlyOnce);
    }

    @PostMapping("/{id}/paypal")
    public ResponseEntity<Void> paypalPayment(@PathVariable("id") Long id, @RequestBody @Valid PaypalRequest request) {
        process(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pagseguro")
    public ResponseEntity<Void> pagseguroPayment(@PathVariable("id") Long id, @RequestBody @Valid PagseguroRequest request) {
        process(id, request);
        return ResponseEntity.ok().build();
    }

    private void process(Long id, PaymentGatewayRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));

        order.addNewTransaction(request.toTransaction(order));
        orderRepository.save(order);

        if (order.hasSucceededTransaction()) {
            succeededEvents.forEach(event -> event.process(order));
            emailService.newPurchase(order);
        } else {
            emailService.retryOrder(order);
        }
    }
}
