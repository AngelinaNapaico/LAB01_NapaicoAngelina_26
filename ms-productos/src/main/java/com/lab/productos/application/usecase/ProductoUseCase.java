package com.lab.productos.application.usecase;

import com.lab.productos.domain.model.Producto;
import com.lab.productos.domain.port.in.ProductoServicePort;
import com.lab.productos.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Caso de uso reactivo — orquesta la lógica de Productos.
 */
@Service
public class ProductoUseCase implements ProductoServicePort {

    private final ProductoRepositoryPort repositoryPort;

    public ProductoUseCase(ProductoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Flux<Producto> listarActivos() {
        return repositoryPort.findByActiveTrue();
    }

    @Override
    public Flux<Producto> listarTodos() {
        return repositoryPort.findAll();
    }

    @Override
    public Mono<Producto> buscarPorId(Long id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Mono<Producto> guardar(Producto producto) {
        producto.setActive(true);
        return repositoryPort.save(producto);
    }

    @Override
    public Mono<Producto> actualizar(Long id, Producto datos) {
        return repositoryPort.findById(id)
                .flatMap(p -> {
                    p.setName(datos.getName());
                    p.setPrice(datos.getPrice());
                    p.setStock(datos.getStock());
                    p.setActive(datos.getActive());
                    return repositoryPort.save(p);
                });
    }

    @Override
    public Mono<Void> desactivar(Long id) {
        return repositoryPort.findById(id)
                .flatMap(p -> {
                    p.setActive(false);
                    return repositoryPort.save(p);
                })
                .then();
    }

    @Override
    public Mono<Void> reducirStock(Long id, Integer cantidad) {
        return repositoryPort.findById(id)
                .flatMap(p -> {
                    p.reducirStock(cantidad);
                    return repositoryPort.save(p);
                })
                .then();
    }
}
