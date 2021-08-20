package br.com.zupacademy.yudi.mercadolivre.external_services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/external-services")
public class ExternalServicesController {

    @PostMapping("/notas-fiscais")
    public void receiveNotaFiscal(@RequestBody @Valid NotaFiscalRequest request) {
        String logNotaFiscal = String.format("Gerando nota fiscal para o pedido com id = %d, para o comprador = %d",
                request.getOrderId(), request.getBuyerId());
        System.out.println(logNotaFiscal);
    }

    @PostMapping("/seller-ranking")
    public void receiveSellerRanking(@RequestBody @Valid SellerRankingRequest request) {
        String logRank = String.format("Rank adicionada ao vendedor com id = %d, pela venda com id = %d",
                request.getSellerId(), request.getOrderId());
        System.out.println(logRank);
    }
}
