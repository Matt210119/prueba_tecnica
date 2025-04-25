package com.prueba.apibanco.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long cuentaId;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String numeroCuenta;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private BigDecimal saldoActual;

    @OneToMany(mappedBy = "cuentaOrdenante")
    private List<Transaccion> transaccionesOrdenadas;

    @OneToMany(mappedBy = "cuentaBeneficiario")
    private List<Transaccion> transaccionesRecibidas;

    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;
}

