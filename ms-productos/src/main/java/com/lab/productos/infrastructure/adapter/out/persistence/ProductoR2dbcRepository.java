package com.lab.productos.infrastructure.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Repositorio R2DBC reactivo — trabaja con ProductoEntity.
 */
public interface ProductoR2dbcRepository extends ReactiveCrudRepository<ProductoEntity, Long> {

    Flux<ProductoEntity> findByActiveTrue();
}
