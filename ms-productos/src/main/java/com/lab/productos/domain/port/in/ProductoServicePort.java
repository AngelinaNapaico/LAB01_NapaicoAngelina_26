package com.lab.productos.domain.port.in;

import com.lab.productos.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada reactivo — define los casos de uso de Productos.
 */
public interface ProductoServicePort {

    Flux<Producto> listarActivos();

    Flux<Producto> listarTodos();

    Mono<Producto> buscarPorId(Long id);

    Mono<Producto> guardar(Producto producto);

    Mono<Producto> actualizar(Long id, Producto datos);

    Mono<Void> desactivar(Long id);

    Mono<Void> reducirStock(Long id, Integer cantidad);
}
