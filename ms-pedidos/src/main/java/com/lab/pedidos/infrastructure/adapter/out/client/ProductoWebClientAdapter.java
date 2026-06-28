package com.lab.pedidos.infrastructure.adapter.out.client;

import com.lab.pedidos.domain.model.ProductoResponse;
import com.lab.pedidos.domain.port.out.ProductoClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Adaptador de salida reactivo — llama a ms-productos usando WebClient.
 */
@Component
public class ProductoWebClientAdapter implements ProductoClientPort {

    private final WebClient webClient;

    public ProductoWebClientAdapter(WebClient.Builder builder,
                                     @Value("${ms.productos.url}") String msProductosUrl) {
        this.webClient = builder.baseUrl(msProductosUrl + "/api/productos").build();
    }

    @Override
    public Mono<ProductoResponse> buscarPorId(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ProductoResponse.class)
                .onErrorResume(e -> Mono.empty());
    }

    @Override
    public Mono<Void> reducirStock(Long id, Integer cantidad) {
        return webClient.put()
                .uri("/{id}/stock?cantidad={cantidad}", id, cantidad)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
