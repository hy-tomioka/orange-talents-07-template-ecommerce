package br.com.zupacademy.yudi.mercadolivre.generics.exceptions;

public class ValidationErrorResponse {

    private String field;
    private String mensagem;

    public ValidationErrorResponse(String field, String mensagem) {
        this.field = field;
        this.mensagem = mensagem;
    }

    public String getField() {
        return field;
    }

    public String getMensagem() {
        return mensagem;
    }
}
