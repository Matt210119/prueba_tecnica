package com.prueba.apibanco.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaccionId;

    @ManyToOne
    @JoinColumn(name = "cuenta_ordenante_id")
    private Cuenta cuentaOrdenante;

    @ManyToOne
    @JoinColumn(name = "cuenta_beneficiario_id")
    private Cuenta cuentaBeneficiario;

    private BigDecimal valor;

    private LocalDateTime fechaProceso;
    private String estado;
    private String referencia;

    @OneToMany(mappedBy = "transaccion")
    private List<Movimiento> movimientos;
}
