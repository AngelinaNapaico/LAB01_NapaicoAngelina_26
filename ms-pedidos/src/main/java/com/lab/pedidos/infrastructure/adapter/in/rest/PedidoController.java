package com.lab.pedidos.infrastructure.adapter.in.rest;

import com.lab.pedidos.domain.model.Pedido;
import com.lab.pedidos.domain.port.in.PedidoServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Adaptador de entrada REST reactivo.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoServicePort servicePort;

    public PedidoController(PedidoServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping
    public Flux<Pedido> listar() {
        return servicePort.listarTodos();
    }

    @GetMapping("/{id}")
    public Mono<Pedido> buscarPorId(@PathVariable Long id) {
        return servicePort.buscarPorId(id);
    }

    // Body: { "productId": 1, "quantity": 2 }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pedido> crear(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf(body.get("productId").toString());
        Integer quantity = Integer.valueOf(body.get("quantity").toString());
        return servicePort.crearPedido(productId, quantity);
    }

    // Body: { "status": "COMPLETED" }
    @PatchMapping("/{id}/estado")
    public Mono<Pedido> actualizarEstado(@PathVariable Long id,
                                         @RequestBody Map<String, String> body) {
        return servicePort.actualizarEstado(id, body.get("status"));
    }

    @GetMapping("/producto/{productId}")
    public Flux<Pedido> listarPorProducto(@PathVariable Long productId) {
        return servicePort.listarPorProducto(productId);
    }
}
