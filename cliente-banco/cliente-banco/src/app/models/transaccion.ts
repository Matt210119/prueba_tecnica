export interface TransaccionRequest {
  cuentaOrdenanteId: number;
  cuentaBeneficiarioId: number;
  valor: number;
  referencia: string;
}

export interface TransaccionResponse {
  nombreApellidoOrdenante: string;
  nombreApellidoBeneficiario: string;
  emailBeneficiario: string;
  fechaProceso: string;
  numeroCuentaOrdenante: string;
  numeroCuentaBeneficiario: string;
  valorTransaccion: number;
  valorTransaccionEnLetras: string;
}