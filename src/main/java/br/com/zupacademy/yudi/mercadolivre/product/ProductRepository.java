package br.com.zupacademy.yudi.mercadolivre.product;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {

    Optional<Product> findById(Long id);

    Product save(Product product);

}
