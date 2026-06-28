package com.lab.pedidos.infrastructure.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Repositorio R2DBC reactivo — trabaja con PedidoEntity.
 */
public interface PedidoR2dbcRepository extends ReactiveCrudRepository<PedidoEntity, Long> {

    Flux<PedidoEntity> findByProductId(Long productId);
}
