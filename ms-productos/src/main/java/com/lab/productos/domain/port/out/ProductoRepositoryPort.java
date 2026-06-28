package com.lab.productos.domain.port.out;

import com.lab.productos.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida reactivo — contrato de persistencia de Productos.
 */
public interface ProductoRepositoryPort {

    Flux<Producto> findAll();

    Flux<Producto> findByActiveTrue();

    Mono<Producto> findById(Long id);

    Mono<Producto> save(Producto producto);
}
