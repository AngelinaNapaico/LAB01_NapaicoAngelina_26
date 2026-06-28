package com.lab.productos.infrastructure.adapter.out.persistence;

import com.lab.productos.domain.model.Producto;
import com.lab.productos.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Adaptador de salida reactivo — implementa ProductoRepositoryPort con R2DBC.
 */
@Component
public class ProductoPersistenceAdapter implements ProductoRepositoryPort {

    private final ProductoR2dbcRepository repository;

    public ProductoPersistenceAdapter(ProductoR2dbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Producto> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    @Override
    public Flux<Producto> findByActiveTrue() {
        return repository.findByActiveTrue().map(this::toDomain);
    }

    @Override
    public Mono<Producto> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return repository.save(toEntity(producto)).map(this::toDomain);
    }

    private ProductoEntity toEntity(Producto p) {
        return ProductoEntity.builder()
                .id(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .stock(p.getStock())
                .active(p.getActive())
                .build();
    }

    private Producto toDomain(ProductoEntity e) {
        return new Producto(e.getId(), e.getName(), e.getPrice(), e.getStock(), e.getActive());
    }
}
