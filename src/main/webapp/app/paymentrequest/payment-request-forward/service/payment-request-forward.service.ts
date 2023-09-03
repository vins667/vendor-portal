import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPaymentRequestForward } from '../payment-request-forward.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import * as moment from 'moment';

export type EntityResponseType = HttpResponse<IPaymentRequestForward>;
export type EntityArrayResponseType = HttpResponse<IPaymentRequestForward[]>;

@Injectable({ providedIn: 'root' })
export class PaymentRequestForwardService {
  protected resourceUrl = SERVER_API_URL + 'api/payment-request-forwards';

  constructor(protected http: HttpClient) {}

  create(paymentRequestForward: IPaymentRequestForward): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentRequestForward);
    return this.http
      .post<IPaymentRequestForward>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paymentRequestForward: IPaymentRequestForward): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentRequestForward);
    return this.http
      .put<IPaymentRequestForward>(`${this.resourceUrl}`, copy, {
        observe: 'response'
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentRequestForward>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentRequestForward[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryByType(flag?: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IPaymentRequestForward[]>(`${this.resourceUrl + '-by-type'}/${flag}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search?: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IPaymentRequestForward[]>(this.resourceUrl + '-filter', search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(paymentRequestForward: IPaymentRequestForward): IPaymentRequestForward {
    return Object.assign({}, paymentRequestForward, {
      // createdDate: paymentRequestForward.createdDate.isValid() ? paymentRequestForward.createdDate.toJSON() : undefined
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((paymentRequestForward: IPaymentRequestForward) => {
        paymentRequestForward.createdDate = paymentRequestForward.createdDate ? moment(paymentRequestForward.createdDate) : undefined;
      });
    }
    return res;
  }
}
