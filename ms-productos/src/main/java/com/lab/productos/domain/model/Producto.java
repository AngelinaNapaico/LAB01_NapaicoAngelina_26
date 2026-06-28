package com.lab.productos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidad de dominio pura — sin anotaciones de framework.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;

    public boolean tieneStock(int cantidad) {
        return this.stock != null && this.stock >= cantidad;
    }

    public void reducirStock(int cantidad) {
        if (!tieneStock(cantidad)) {
            throw new IllegalStateException("Stock insuficiente");
        }
        this.stock -= cantidad;
    }
}
