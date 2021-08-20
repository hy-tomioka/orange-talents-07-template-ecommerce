package br.com.zupacademy.yudi.mercadolivre.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    private final String password;

    private Password(String rawPassword) {
        this.password = new BCryptPasswordEncoder().encode(rawPassword);
    }

    /**
     *
     * @param rawPassword is the password from requests.
     */
    public static Password encode(String rawPassword) {
        return new Password(rawPassword);
    }

    public String get() {
        return this.password;
    }
}
