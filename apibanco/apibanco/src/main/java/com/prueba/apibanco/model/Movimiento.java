package com.prueba.apibanco.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoId;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoMovimiento tipo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaMovimiento categoria;

    private BigDecimal monto;

    private LocalDateTime fechaMovimiento;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;
}
