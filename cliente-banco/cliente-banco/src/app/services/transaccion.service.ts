import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { TransaccionRequest, TransaccionResponse } from '../models/transaccion';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {
  private apiUrl = 'http://localhost:8080/api/transacciones';

  constructor(private http: HttpClient) {}

  procesarTransaccion(request: TransaccionRequest): Observable<TransaccionResponse> {
    return this.http.post<TransaccionResponse>(`${this.apiUrl}/procesar-transaccion`, request)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    const mensaje = error.error?.message || 'Error desconocido en el servidor.';
    return throwError(() => new Error(mensaje));
  }
}