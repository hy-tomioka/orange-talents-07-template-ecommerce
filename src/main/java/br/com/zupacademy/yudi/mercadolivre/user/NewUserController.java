package br.com.zupacademy.yudi.mercadolivre.user;

import br.com.zupacademy.yudi.mercadolivre.user.dto.NewUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class NewUserController {

    private final UserRepository userRepository;

    public NewUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewUserRequest request) {
        User newUser = request.toModel();
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
