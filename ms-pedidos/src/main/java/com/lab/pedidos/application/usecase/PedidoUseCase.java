package com.lab.pedidos.application.usecase;

import com.lab.pedidos.domain.model.Pedido;
import com.lab.pedidos.domain.port.in.PedidoServicePort;
import com.lab.pedidos.domain.port.out.PedidoRepositoryPort;
import com.lab.pedidos.domain.port.out.ProductoClientPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Caso de uso reactivo — orquesta la lógica de Pedidos.
 */
@Service
public class PedidoUseCase implements PedidoServicePort {

    private final PedidoRepositoryPort repositoryPort;
    private final ProductoClientPort productoClientPort;

    public PedidoUseCase(PedidoRepositoryPort repositoryPort,
                         ProductoClientPort productoClientPort) {
        this.repositoryPort = repositoryPort;
        this.productoClientPort = productoClientPort;
    }

    @Override
    public Flux<Pedido> listarTodos() {
        return repositoryPort.findAll();
    }

    @Override
    public Mono<Pedido> buscarPorId(Long id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Mono<Pedido> crearPedido(Long productId, Integer quantity) {
        return productoClientPort.buscarPorId(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado: " + productId)))
                .flatMap(producto -> {
                    if (!Boolean.TRUE.equals(producto.getActive())) {
                        return Mono.error(new RuntimeException("Producto inactivo: " + productId));
                    }
                    if (producto.getStock() < quantity) {
                        return Mono.error(new RuntimeException("Stock insuficiente. Disponible: " + producto.getStock()));
                    }

                    var total = producto.getPrice().multiply(java.math.BigDecimal.valueOf(quantity));

                    return productoClientPort.reducirStock(productId, quantity)
                            .then(repositoryPort.save(Pedido.builder()
                                    .productId(productId)
                                    .quantity(quantity)
                                    .total(total)
                                    .status("PENDING")
                                    .date(LocalDateTime.now())
                                    .build()));
                });
    }

    @Override
    public Mono<Pedido> actualizarEstado(Long id, String status) {
        return repositoryPort.findById(id)
                .flatMap(p -> {
                    p.setStatus(status);
                    return repositoryPort.save(p);
                });
    }

    @Override
    public Flux<Pedido> listarPorProducto(Long productId) {
        return repositoryPort.findByProductId(productId);
    }
}
