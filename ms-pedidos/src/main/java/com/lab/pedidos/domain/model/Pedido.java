package com.lab.pedidos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad de dominio pura — sin anotaciones de framework.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal total;
    private String status;
    private LocalDateTime date;
}
