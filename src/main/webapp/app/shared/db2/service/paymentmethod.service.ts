import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IPaymentmethod } from 'app/shared/db2/model/paymentmethod.model';

type EntityResponseType = HttpResponse<IPaymentmethod>;
type EntityResponseArrayType = HttpResponse<IPaymentmethod[]>;
@Injectable({ providedIn: 'root' })
export class PaymentmethodService {
  public resourceUrl = SERVER_API_URL + 'api/db2-paymentmethods';

  constructor(protected http: HttpClient) {}

  query(): Observable<EntityResponseArrayType> {
    return this.http.get<IPaymentmethod[]>(`${this.resourceUrl}`, { observe: 'response' });
  }

  find(paymenttermcode: string): Observable<EntityResponseType> {
    return this.http.get<IPaymentmethod>(`${this.resourceUrl}/${paymenttermcode}`, { observe: 'response' });
  }
}
