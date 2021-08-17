package br.com.zupacademy.yudi.mercadolivre.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String username);
}
