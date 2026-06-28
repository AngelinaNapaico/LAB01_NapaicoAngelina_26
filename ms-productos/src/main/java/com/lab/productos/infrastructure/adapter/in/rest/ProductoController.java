package com.lab.productos.infrastructure.adapter.in.rest;

import com.lab.productos.domain.model.Producto;
import com.lab.productos.domain.port.in.ProductoServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Adaptador de entrada REST reactivo.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoServicePort servicePort;

    public ProductoController(ProductoServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping
    public Flux<Producto> listar() {
        return servicePort.listarActivos();
    }

    @GetMapping("/todos")
    public Flux<Producto> listarTodos() {
        return servicePort.listarTodos();
    }

    @GetMapping("/{id}")
    public Mono<Producto> buscarPorId(@PathVariable Long id) {
        return servicePort.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> crear(@RequestBody Producto producto) {
        return servicePort.guardar(producto);
    }

    @PutMapping("/{id}")
    public Mono<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return servicePort.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> desactivar(@PathVariable Long id) {
        return servicePort.desactivar(id);
    }

    @PutMapping("/{id}/stock")
    public Mono<Void> reducirStock(@PathVariable Long id,
                                    @RequestParam Integer cantidad) {
        return servicePort.reducirStock(id, cantidad);
    }
}
