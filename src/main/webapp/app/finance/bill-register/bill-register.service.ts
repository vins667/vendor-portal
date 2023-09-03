import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBillRegister, getBillRegisterIdentifier } from './bill-register.model';
import { IBillRegisterSearch } from 'app/finance/bill-register/bill-register-search.model';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { IPlantinvoiceSearch } from 'app/finance/bill-register/plantinvoice-search.model';
import { IPlantinvoice } from 'app/finance/bill-register/plantinvoice.model';

export type EntityResponseType = HttpResponse<IBillRegister>;
export type EntityArrayResponseType = HttpResponse<IBillRegister[]>;

@Injectable({ providedIn: 'root' })
export class BillRegisterService {
  public resourceUrl = SERVER_API_URL + 'api/bill-registers';
  public resourceUrlFilter = SERVER_API_URL + 'api/bill-registers-filter';
  public resourceUrlPlantinvoices = SERVER_API_URL + 'api/plantinvoices-filter';
  public resourceUrlPlantinvoice = SERVER_API_URL + 'api/plantinvoices-bill-register';
  public resourceUrlSupplier = SERVER_API_URL + 'api/vieworderpartners/';

  constructor(protected http: HttpClient) {}

  create(billRegister: IBillRegister): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegister);
    return this.http
      .post<IBillRegister>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(billRegister: IBillRegister): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegister);
    return this.http
      .put<IBillRegister>(`${this.resourceUrl}/${getBillRegisterIdentifier(billRegister) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(billRegister: IBillRegister): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegister);
    return this.http
      .patch<IBillRegister>(`${this.resourceUrl}/${getBillRegisterIdentifier(billRegister) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillRegister>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByCode(code: string): Observable<EntityResponseType> {
    return this.http
      .get<IBillRegister>(`${this.resourceUrlPlantinvoice}/${code}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillRegister[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IBillRegisterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IBillRegister[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  invoiceQuery(search: IPlantinvoiceSearch): Observable<HttpResponse<IPlantinvoice[]>> {
    return this.http.post<IPlantinvoice[]>(this.resourceUrlPlantinvoices, search, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(billRegister: IBillRegister): IBillRegister {
    return Object.assign({}, billRegister, {
      invoicedate: billRegister.invoicedate.isValid() ? billRegister.invoicedate.format(DATE_FORMAT) : undefined
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.invoicedate = res.body.invoicedate ? moment(res.body.invoicedate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((billRegister: IBillRegister) => {
        billRegister.invoicedate = billRegister.invoicedate ? moment(billRegister.invoicedate) : undefined;
      });
    }
    return res;
  }
}
