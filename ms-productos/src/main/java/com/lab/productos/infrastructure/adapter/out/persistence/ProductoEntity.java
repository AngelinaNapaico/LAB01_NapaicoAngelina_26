package com.lab.productos.infrastructure.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * Entidad R2DBC — mapea la tabla "productos" en PostgreSQL.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("productos")
public class ProductoEntity {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("price")
    private BigDecimal price;

    @Column("stock")
    private Integer stock;

    @Column("active")
    private Boolean active;
}
