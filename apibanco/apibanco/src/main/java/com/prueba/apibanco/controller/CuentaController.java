package com.prueba.apibanco.controller;

import com.prueba.apibanco.DTO.CuentaValidadaDTO;
import com.prueba.apibanco.model.Cuenta;
import com.prueba.apibanco.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/cuentavalidada")
    public ResponseEntity<CuentaValidadaDTO> obtenerNombreCliente(@RequestParam String numeroCuenta) {
        return cuentaService.obtenerNombreClientePorNumeroCuenta(numeroCuenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}