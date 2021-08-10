package br.com.zupacademy.yudi.mercadolivre.user;

import br.com.zupacademy.yudi.mercadolivre.user.dto.NewUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class NewUserController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewUserRequest request) {
        User newUser = request.toModel();
        em.persist(newUser);
        return ResponseEntity.ok().build();
    }
}
