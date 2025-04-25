package com.prueba.apibanco.repository;

import com.prueba.apibanco.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query(value = "SELECT c.cuenta_id,c.saldo_actual, cl.nombre || ' ' || cl.apellido  FROM cuentas c JOIN clientes cl ON c.cliente_id = cl.cliente_id WHERE c.numero_cuenta = :numeroCuenta", nativeQuery = true)
    List<Object[]> findNombreClienteByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);

}

