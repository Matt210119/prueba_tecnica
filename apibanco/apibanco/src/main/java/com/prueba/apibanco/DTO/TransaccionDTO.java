package com.prueba.apibanco.DTO;

import java.math.BigDecimal;

public class TransaccionDTO {
    private Long cuentaOrdenanteId;
    private Long cuentaBeneficiarioId;
    private BigDecimal valor;
    private String referencia;

    // Getters and Setters
    public Long getCuentaOrdenanteId() {
        return cuentaOrdenanteId;
    }

    public void setCuentaOrdenanteId(Long cuentaOrdenanteId) {
        this.cuentaOrdenanteId = cuentaOrdenanteId;
    }

    public Long getCuentaBeneficiarioId() {
        return cuentaBeneficiarioId;
    }

    public void setCuentaBeneficiarioId(Long cuentaBeneficiarioId) {
        this.cuentaBeneficiarioId = cuentaBeneficiarioId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}

