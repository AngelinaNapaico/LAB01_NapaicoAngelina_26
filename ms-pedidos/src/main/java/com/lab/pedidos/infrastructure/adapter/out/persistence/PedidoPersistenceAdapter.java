package com.lab.pedidos.infrastructure.adapter.out.persistence;

import com.lab.pedidos.domain.model.Pedido;
import com.lab.pedidos.domain.port.out.PedidoRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Adaptador de salida reactivo — implementa PedidoRepositoryPort con R2DBC.
 */
@Component
public class PedidoPersistenceAdapter implements PedidoRepositoryPort {

    private final PedidoR2dbcRepository repository;

    public PedidoPersistenceAdapter(PedidoR2dbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Pedido> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    @Override
    public Mono<Pedido> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Mono<Pedido> save(Pedido pedido) {
        return repository.save(toEntity(pedido)).map(this::toDomain);
    }

    @Override
    public Flux<Pedido> findByProductId(Long productId) {
        return repository.findByProductId(productId).map(this::toDomain);
    }

    private PedidoEntity toEntity(Pedido p) {
        return PedidoEntity.builder()
                .id(p.getId())
                .productId(p.getProductId())
                .quantity(p.getQuantity())
                .total(p.getTotal())
                .status(p.getStatus())
                .date(p.getDate())
                .build();
    }

    private Pedido toDomain(PedidoEntity e) {
        return new Pedido(e.getId(), e.getProductId(), e.getQuantity(),
                e.getTotal(), e.getStatus(), e.getDate());
    }
}
