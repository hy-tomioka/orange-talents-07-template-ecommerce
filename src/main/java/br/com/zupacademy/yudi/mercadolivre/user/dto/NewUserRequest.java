package br.com.zupacademy.yudi.mercadolivre.user.dto;

import br.com.zupacademy.yudi.mercadolivre.generics.validation.UniqueValue;
import br.com.zupacademy.yudi.mercadolivre.user.Password;
import br.com.zupacademy.yudi.mercadolivre.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserRequest {

    @NotBlank @Email
    @UniqueValue(domainClass = User.class, fieldName = "login")
    private String login;

    @NotBlank @Size(min = 6)
    private String password;

    public NewUserRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel() {
        return new User(login, Password.encode(password));
    }
}
