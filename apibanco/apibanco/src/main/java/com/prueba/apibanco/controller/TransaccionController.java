package com.prueba.apibanco.controller;

import com.prueba.apibanco.DTO.TransaccionDTO;
import com.prueba.apibanco.DTO.TransaccionResumenDTO;
import com.prueba.apibanco.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/procesar-transaccion")
    public ResponseEntity<?> procesar(@RequestBody TransaccionDTO transaccionDTO) {
        // Validar el valor de la transacción
        if (transaccionDTO.getValor().compareTo(BigDecimal.valueOf(100.00)) > 0) {
            // Retornar un mensaje claro de error
            return ResponseEntity.badRequest().body("El monto de la transacción no puede exceder los 100.00.");
        }

        // Procesar la transacción
        TransaccionResumenDTO resumen = transaccionService.procesarTransaccion(
                transaccionDTO.getCuentaOrdenanteId(),
                transaccionDTO.getCuentaBeneficiarioId(),
                transaccionDTO.getValor(),
                transaccionDTO.getReferencia()
        );

        return ResponseEntity.ok(resumen);
    }
}

