package com.lab.pedidos.infrastructure.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad R2DBC — mapea la tabla "pedidos" en PostgreSQL.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("pedidos")
public class PedidoEntity {

    @Id
    private Long id;

    @Column("product_id")
    private Long productId;

    @Column("quantity")
    private Integer quantity;

    @Column("total")
    private BigDecimal total;

    @Column("status")
    private String status;

    @Column("date")
    private LocalDateTime date;
}
