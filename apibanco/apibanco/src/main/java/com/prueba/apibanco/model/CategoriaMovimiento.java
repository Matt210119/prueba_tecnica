package com.prueba.apibanco.model;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "categorias_movimiento")
public class CategoriaMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Movimiento> movimientos;
}

