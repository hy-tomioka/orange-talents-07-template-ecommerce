package br.com.zupacademy.yudi.mercadolivre.email;

import javax.validation.constraints.NotBlank;

public class Email {

    private String body;
    private String subject;
    private String nameFrom;
    private String from;
    private String to;

    /**
     * @param body     email body
     * @param subject  email subject
     * @param nameFrom name from email sender
     * @param from     email sender
     * @param to       email receiver
     */
    public Email(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom,
                 @NotBlank @javax.validation.constraints.Email String from,
                 @NotBlank @javax.validation.constraints.Email String to) {
        this.body = body;
        this.subject = subject;
        this.nameFrom = nameFrom;
        this.from = from;
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
