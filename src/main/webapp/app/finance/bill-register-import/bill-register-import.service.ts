import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBillRegisterImport, getBillRegisterImportIdentifier } from './bill-register-import.model';
import { IBillRegisterImportSearch } from 'app/finance/bill-register-import/bill-register-import-search.model';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { IPlantinvoiceSearch } from 'app/finance/bill-register-import/plantinvoice-search.model';
import { IPlantinvoice } from 'app/finance/bill-register-import/plantinvoice.model';

export type EntityResponseType = HttpResponse<IBillRegisterImport>;
export type EntityArrayResponseType = HttpResponse<IBillRegisterImport[]>;

@Injectable({ providedIn: 'root' })
export class BillRegisterImportService {
  public resourceUrl = SERVER_API_URL + 'api/bill-register-imports';
  public resourceUrlFilter = SERVER_API_URL + 'api/bill-register-imports-filter';
  public resourceUrlPlantinvoices = SERVER_API_URL + 'api/plantinvoices-filter';
  public resourceUrlPlantinvoice = SERVER_API_URL + 'api/plantinvoices-bill-register-import';
  public resourceUrlSupplier = SERVER_API_URL + 'api/vieworderpartners/';

  constructor(protected http: HttpClient) {}

  create(billRegister: IBillRegisterImport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegister);
    return this.http
      .post<IBillRegisterImport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(billRegister: IBillRegisterImport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegister);
    return this.http
      .put<IBillRegisterImport>(`${this.resourceUrl}/${getBillRegisterImportIdentifier(billRegister) as number}`, copy, {
        observe: 'response'
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(billRegister: IBillRegisterImport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegister);
    return this.http
      .patch<IBillRegisterImport>(`${this.resourceUrl}/${getBillRegisterImportIdentifier(billRegister) as number}`, copy, {
        observe: 'response'
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillRegisterImport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByCode(code: string): Observable<EntityResponseType> {
    return this.http
      .get<IBillRegisterImport>(`${this.resourceUrlPlantinvoice}/${code}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillRegisterImport[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IBillRegisterImportSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IBillRegisterImport[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  invoiceQuery(search: IPlantinvoiceSearch): Observable<HttpResponse<IPlantinvoice[]>> {
    return this.http.post<IPlantinvoice[]>(this.resourceUrlPlantinvoices, search, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(billRegister: IBillRegisterImport): IBillRegisterImport {
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
      res.body.forEach((billRegister: IBillRegisterImport) => {
        billRegister.invoicedate = billRegister.invoicedate ? moment(billRegister.invoicedate) : undefined;
      });
    }
    return res;
  }
}
