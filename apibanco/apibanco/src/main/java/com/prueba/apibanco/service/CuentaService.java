package com.prueba.apibanco.service;


import com.prueba.apibanco.DTO.CuentaValidadaDTO;
import com.prueba.apibanco.model.Cuenta;
import com.prueba.apibanco.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Optional<CuentaValidadaDTO> obtenerNombreClientePorNumeroCuenta(String numeroCuenta) {
        List<Object[]> results = cuentaRepository.findNombreClienteByNumeroCuenta(numeroCuenta);

        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            Long idCuenta = ((Number) result[0]).longValue(); // asegurarse de convertir a Long correctamente
            BigDecimal saldoActual = (BigDecimal) result[1];
            String nombre = (String) result[2];

            return Optional.of(new CuentaValidadaDTO(nombre, idCuenta, saldoActual));
        } else {
            return Optional.empty();
        }
    }

}
