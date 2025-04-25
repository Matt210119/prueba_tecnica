package com.prueba.apibanco.service;

import com.prueba.apibanco.DTO.TransaccionResumenDTO;
import com.prueba.apibanco.model.*;
import com.prueba.apibanco.repository.CuentaRepository;
import com.prueba.apibanco.repository.MovimientoRepository;
import com.prueba.apibanco.repository.TransaccionRepository;
import com.prueba.apibanco.utils.NumberToWords;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransaccionService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional
    public TransaccionResumenDTO procesarTransaccion(Long cuentaOrdenanteId, Long cuentaBeneficiarioId, BigDecimal valor, String referencia) {
        // Verificar que la cuenta ordenante tiene suficiente saldo
        Cuenta cuentaOrdenante = cuentaRepository.findById(cuentaOrdenanteId).orElseThrow(() -> new RuntimeException("Cuenta ordenante no encontrada"));
        Cuenta cuentaBeneficiario = cuentaRepository.findById(cuentaBeneficiarioId).orElseThrow(() -> new RuntimeException("Cuenta beneficiario no encontrada"));

        if (cuentaOrdenante.getSaldoActual().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente en la cuenta ordenante.");
        }

        // Crear la transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaOrdenante(cuentaOrdenante);
        transaccion.setCuentaBeneficiario(cuentaBeneficiario);
        transaccion.setValor(valor);
        transaccion.setFechaProceso(LocalDateTime.now());
        transaccion.setEstado("COMPLETADA");
        transaccion.setReferencia(referencia);

        // Guardar la transacción
        transaccionRepository.save(transaccion);
        // Movimiento del ordenante (Débito)
        TipoMovimiento tipoDebito = new TipoMovimiento();
        tipoDebito.setTipoId(2L);
        tipoDebito.setNombre("Débito");

        CategoriaMovimiento categoriaTransferencia = new CategoriaMovimiento();
        categoriaTransferencia.setCategoriaId(3L);
        categoriaTransferencia.setNombre("Transferencia");

        // Crear los movimientos de las cuentas
        Movimiento movimientoOrdenante = new Movimiento();
        movimientoOrdenante.setCuenta(cuentaOrdenante);
        movimientoOrdenante.setTipo(tipoDebito); // Débito
        movimientoOrdenante.setCategoria(categoriaTransferencia); // Transferencia
        movimientoOrdenante.setMonto(valor);
        movimientoOrdenante.setDescripcion("Transferencia a " + cuentaBeneficiario.getCliente().getNombre() + " " + cuentaBeneficiario.getCliente().getApellido());
        movimientoOrdenante.setTransaccion(transaccion);
        movimientoOrdenante.setFechaMovimiento(LocalDateTime.now());

        // Movimiento del beneficiario (Crédito)
        TipoMovimiento tipoCredito = new TipoMovimiento();
        tipoCredito.setTipoId(1L);
        tipoCredito.setNombre("Crédito");

        Movimiento movimientoBeneficiario = new Movimiento();
        movimientoBeneficiario.setCuenta(cuentaBeneficiario);
        movimientoBeneficiario.setTipo(tipoCredito); // Crédito
        movimientoBeneficiario.setCategoria(categoriaTransferencia); // Transferencia
        movimientoBeneficiario.setMonto(valor);
        movimientoBeneficiario.setDescripcion("Transferencia recibida de " + cuentaOrdenante.getCliente().getNombre() + " " + cuentaOrdenante.getCliente().getApellido());
        movimientoBeneficiario.setTransaccion(transaccion);
        movimientoBeneficiario.setFechaMovimiento(LocalDateTime.now());

        // Guardar los movimientos
        movimientoRepository.save(movimientoOrdenante);
        movimientoRepository.save(movimientoBeneficiario);

        // Actualizar los saldos de las cuentas
        cuentaOrdenante.setSaldoActual(cuentaOrdenante.getSaldoActual().subtract(valor));
        cuentaBeneficiario.setSaldoActual(cuentaBeneficiario.getSaldoActual().add(valor));

        // Guardar las cuentas actualizadas
        cuentaRepository.save(cuentaOrdenante);
        cuentaRepository.save(cuentaBeneficiario);

        // Preparar el resumen
        TransaccionResumenDTO resumen = new TransaccionResumenDTO();
        resumen.setNombreApellidoOrdenante(cuentaOrdenante.getCliente().getNombre() + " " + cuentaOrdenante.getCliente().getApellido());
        resumen.setNombreApellidoBeneficiario(cuentaBeneficiario.getCliente().getNombre() + " " + cuentaBeneficiario.getCliente().getApellido());
        resumen.setEmailBeneficiario(cuentaBeneficiario.getCliente().getEmail());
        resumen.setFechaProceso(transaccion.getFechaProceso().toLocalDate().toString());
        resumen.setNumeroCuentaOrdenante(cuentaOrdenante.getNumeroCuenta());
        resumen.setNumeroCuentaBeneficiario(cuentaBeneficiario.getNumeroCuenta());
        resumen.setValorTransaccion(valor);
        resumen.setValorTransaccionEnLetras(NumberToWords.convert(valor));

        return resumen;
    }
}

