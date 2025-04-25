package com.prueba.apibanco.DTO;

import java.math.BigDecimal;

public class CuentaValidadaDTO {
    private String nombre;
    private Long idCuenta;
    private BigDecimal saldoActual;  // Asumiendo que saldoActual es un tipo BigDecimal

    public CuentaValidadaDTO(String nombre, Long idCuenta, BigDecimal saldoActual) {
        this.nombre = nombre;
        this.idCuenta = idCuenta;
        this.saldoActual = saldoActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }
}
