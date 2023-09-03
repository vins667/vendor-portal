import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IDirectBookingSearch } from 'app/shared/model/direct-booking-search.model';

@Injectable({ providedIn: 'root' })
export class PaymentRequestReportService {
  public resourceUrl = SERVER_API_URL + 'api/payment-request-report';

  constructor(protected http: HttpClient) {}

  downloadPdf(search: IDirectBookingSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
