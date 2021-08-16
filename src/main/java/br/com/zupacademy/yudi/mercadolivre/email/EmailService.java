package br.com.zupacademy.yudi.mercadolivre.email;

import br.com.zupacademy.yudi.mercadolivre.product.ProductQuestion;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void newQuestion(ProductQuestion question) {

        Email email = question.formatToEmail();
        mailSender.send(email);
    }
}
