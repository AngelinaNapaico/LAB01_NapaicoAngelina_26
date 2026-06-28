package com.lab.pedidos.domain.port.out;

import com.lab.pedidos.domain.model.ProductoResponse;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida reactivo — contrato para consultar ms-productos.
 */
public interface ProductoClientPort {

    Mono<ProductoResponse> buscarPorId(Long id);

    Mono<Void> reducirStock(Long id, Integer cantidad);
}
