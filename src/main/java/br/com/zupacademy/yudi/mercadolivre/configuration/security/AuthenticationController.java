package br.com.zupacademy.yudi.mercadolivre.configuration.security;

import br.com.zupacademy.yudi.mercadolivre.configuration.security.dto.LoginRequest;
import br.com.zupacademy.yudi.mercadolivre.configuration.security.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken userCredatials = request.toUserCredentials();
        Authentication authentication = authenticationManager.authenticate(userCredatials);
        String token = tokenService.generate(authentication);
        return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
    }
}
