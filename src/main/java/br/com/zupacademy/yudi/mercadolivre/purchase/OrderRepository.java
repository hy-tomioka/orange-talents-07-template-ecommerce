package br.com.zupacademy.yudi.mercadolivre.purchase;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {
    Order save(Order order);

    Optional<Order> findById(Long id);
}
