package br.com.zupacademy.yudi.mercadolivre.configuration.security.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank @Email
    private String login;

    @NotBlank
    private String password;

    public UsernamePasswordAuthenticationToken toUserCredentials() {
        return new UsernamePasswordAuthenticationToken(this.login, this.password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
