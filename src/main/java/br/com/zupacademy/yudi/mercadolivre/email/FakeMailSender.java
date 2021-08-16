package br.com.zupacademy.yudi.mercadolivre.email;

import org.springframework.stereotype.Component;

@Component
public class FakeMailSender implements MailSender {

    @Override
    public void send(Email email) {
        System.out.println("Subject: " + email.getSubject());
        System.out.println("Body: " + email.getBody());
        System.out.println("Sender Name: " + email.getNameFrom());
        System.out.println("Sender Email: " + email.getFrom());
        System.out.println("Destiny: " + email.getTo());
    }
}
