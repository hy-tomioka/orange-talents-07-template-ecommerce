package br.com.zupacademy.yudi.mercadolivre.order;

import java.net.URI;
import java.util.UUID;
import java.util.stream.Stream;

public enum PaymentGateway {

    PAYPAL("paypal") {
        @Override
        public URI generateUri(UUID uuid, String redirectUrl) {
            return URI.create("paypal.com?buyerId=" + uuid + "&redirectUrl=" + redirectUrl);
        }
    },
    PAGSEGURO("pagseguro") {
        @Override
        public URI generateUri(UUID uuid, String redirectUrl) {
            return URI.create("pagseguro.com?returnId=" + uuid + "&redirectUrl=" + redirectUrl);
        }
    };

    private final String value;

    PaymentGateway(String value) {
        this.value = value;
    }

    public static PaymentGateway fromValue(String value) {
        return Stream.of(values()).filter(v -> v.name().equals(value.toUpperCase())).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Must be a valid payment gateway."));
    }

    public abstract URI generateUri(UUID uuid, String redirectUrl);

    public String getValue() {
        return value;
    }
}
