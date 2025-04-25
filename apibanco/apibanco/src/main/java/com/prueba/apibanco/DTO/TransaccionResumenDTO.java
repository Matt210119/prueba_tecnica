package com.prueba.apibanco.DTO;

import java.math.BigDecimal;

public class TransaccionResumenDTO {
    private String nombreApellidoOrdenante;
    private String nombreApellidoBeneficiario;
    private String emailBeneficiario;
    private String fechaProceso;
    private String numeroCuentaOrdenante;
    private String numeroCuentaBeneficiario;
    private BigDecimal valorTransaccion;
    private String valorTransaccionEnLetras;

    // Getters and Setters
    public String getNombreApellidoOrdenante() {
        return nombreApellidoOrdenante;
    }

    public void setNombreApellidoOrdenante(String nombreApellidoOrdenante) {
        this.nombreApellidoOrdenante = nombreApellidoOrdenante;
    }

    public String getNombreApellidoBeneficiario() {
        return nombreApellidoBeneficiario;
    }

    public void setNombreApellidoBeneficiario(String nombreApellidoBeneficiario) {
        this.nombreApellidoBeneficiario = nombreApellidoBeneficiario;
    }

    public String getEmailBeneficiario() {
        return emailBeneficiario;
    }

    public void setEmailBeneficiario(String emailBeneficiario) {
        this.emailBeneficiario = emailBeneficiario;
    }

    public String getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getNumeroCuentaOrdenante() {
        return numeroCuentaOrdenante;
    }

    public void setNumeroCuentaOrdenante(String numeroCuentaOrdenante) {
        this.numeroCuentaOrdenante = numeroCuentaOrdenante;
    }

    public String getNumeroCuentaBeneficiario() {
        return numeroCuentaBeneficiario;
    }

    public void setNumeroCuentaBeneficiario(String numeroCuentaBeneficiario) {
        this.numeroCuentaBeneficiario = numeroCuentaBeneficiario;
    }

    public BigDecimal getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(BigDecimal valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public String getValorTransaccionEnLetras() {
        return valorTransaccionEnLetras;
    }

    public void setValorTransaccionEnLetras(String valorTransaccionEnLetras) {
        this.valorTransaccionEnLetras = valorTransaccionEnLetras;
    }
}

