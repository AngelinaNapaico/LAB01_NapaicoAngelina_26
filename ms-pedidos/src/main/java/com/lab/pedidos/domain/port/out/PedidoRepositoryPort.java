package com.lab.pedidos.domain.port.out;

import com.lab.pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida reactivo — contrato de persistencia de Pedidos.
 */
public interface PedidoRepositoryPort {

    Flux<Pedido> findAll();

    Mono<Pedido> findById(Long id);

    Mono<Pedido> save(Pedido pedido);

    Flux<Pedido> findByProductId(Long productId);
}
