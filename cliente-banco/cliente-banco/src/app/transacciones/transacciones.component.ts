import { Component } from '@angular/core';
import { TransaccionService } from '../services/transaccion.service';
import { TransaccionRequest, TransaccionResponse } from '../models/transaccion';

@Component({
  selector: 'app-transacciones',
  templateUrl: './transacciones.component.html'
})
export class TransaccionesComponent {
  request: TransaccionRequest = {
    cuentaOrdenanteId: 0,
    cuentaBeneficiarioId: 0,
    valor: 0,
    referencia: ''
  };

  respuesta?: TransaccionResponse;
  error?: string;

  constructor(private transaccionService: TransaccionService) {}

  enviar() {
    this.transaccionService.procesarTransaccion(this.request).subscribe({
      next: (res) => {
        this.respuesta = res;
        this.error = undefined;
      },
      error: (err) => {
        this.respuesta = undefined;
        this.error = err.message;
      }
    });
  }
}