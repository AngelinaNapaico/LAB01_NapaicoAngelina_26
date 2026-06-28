package com.lab.pedidos.domain.port.in;

import com.lab.pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada reactivo — define los casos de uso de Pedidos.
 */
public interface PedidoServicePort {

    Flux<Pedido> listarTodos();

    Mono<Pedido> buscarPorId(Long id);

    Mono<Pedido> crearPedido(Long productId, Integer quantity);

    Mono<Pedido> actualizarEstado(Long id, String status);

    Flux<Pedido> listarPorProducto(Long productId);
}
