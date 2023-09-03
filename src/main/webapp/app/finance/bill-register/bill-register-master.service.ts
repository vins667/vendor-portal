import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBillRegisterMaster } from './bill-register-master.model';
import { IBillRegister } from 'app/finance/bill-register/bill-register.model';
import { IBillRegisterSearch } from 'app/finance/bill-register/bill-register-search.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';
import { ISalarySearch } from 'app/shared/model/salary-search.model';

type EntityResponseType = HttpResponse<IBillRegisterMaster>;
type EntityArrayResponseType = HttpResponse<IBillRegisterMaster[]>;

@Injectable({ providedIn: 'root' })
export class BillRegisterMasterService {
  public resourceUrl = SERVER_API_URL + 'api/bill-register-masters';
  public resourceUrlReceived = SERVER_API_URL + 'api/bill-register-masters-received';
  public resourceUrlDetails = SERVER_API_URL + 'api/bill-register-details';
  public resourceUrlPlantinvoices = SERVER_API_URL + 'api/plantinvoices/';
  public resourceUrlFilter = SERVER_API_URL + 'api/bill-registers-master-filter';
  public resourceUrlSupplier = SERVER_API_URL + 'api/vieworderpartners/';
  public resourceUrlPlantinvoice = SERVER_API_URL + 'api/plantinvoices-bill-register';

  constructor(protected http: HttpClient) {}

  create(billRegisterMaster: IBillRegisterMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegisterMaster);
    return this.http
      .post<IBillRegisterMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByCode(search: IMasterSearch): Observable<HttpResponse<any>> {
    return this.http.post(`${this.resourceUrlPlantinvoice}`, search, { observe: 'response' });
  }

  update(id: number, billRegisterMaster: IBillRegisterMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegisterMaster);
    return this.http
      .put<IBillRegisterMaster>(`${this.resourceUrl}/${id}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  submit(billRegisterMasters: IBillRegisterMaster[]): Observable<EntityArrayResponseType> {
    return this.http.post<IBillRegisterMaster[]>(`${this.resourceUrlReceived}`, billRegisterMasters, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillRegisterMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillRegisterMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IBillRegisterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IBillRegisterMaster[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadXls(search: IBillRegisterSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrlFilter + '-report'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetails(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(billRegisterMaster: IBillRegisterMaster): IBillRegisterMaster {
    const copy: IBillRegisterMaster = Object.assign({}, billRegisterMaster, {
      billdate:
        billRegisterMaster.billdate != null && billRegisterMaster.billdate.isValid()
          ? billRegisterMaster.billdate.format(DATE_FORMAT)
          : null,
      receiveDate:
        billRegisterMaster.receiveDate != null && billRegisterMaster.receiveDate.isValid()
          ? billRegisterMaster.receiveDate.format(DATE_FORMAT)
          : null,
      submitDate:
        billRegisterMaster.submitDate != null && billRegisterMaster.submitDate.isValid()
          ? billRegisterMaster.submitDate.format(DATE_FORMAT)
          : null,
      createddate:
        billRegisterMaster.createddate != null && billRegisterMaster.createddate.isValid() ? billRegisterMaster.createddate.toJSON() : null,
      updateddate:
        billRegisterMaster.updateddate != null && billRegisterMaster.updateddate.isValid() ? billRegisterMaster.updateddate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.billdate = res.body.billdate != null ? moment(res.body.billdate) : null;
      res.body.receiveDate = res.body.receiveDate != null ? moment(res.body.receiveDate) : null;
      res.body.submitDate = res.body.submitDate != null ? moment(res.body.submitDate) : null;
      res.body.createddate = res.body.createddate != null ? moment(res.body.createddate) : null;
      res.body.updateddate = res.body.updateddate != null ? moment(res.body.updateddate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((billRegisterMaster: IBillRegisterMaster) => {
        billRegisterMaster.billdate = billRegisterMaster.billdate != null ? moment(billRegisterMaster.billdate) : null;
        billRegisterMaster.receiveDate = billRegisterMaster.receiveDate != null ? moment(billRegisterMaster.receiveDate) : null;
        billRegisterMaster.submitDate = billRegisterMaster.submitDate != null ? moment(billRegisterMaster.submitDate) : null;
        billRegisterMaster.createddate = billRegisterMaster.createddate != null ? moment(billRegisterMaster.createddate) : null;
        billRegisterMaster.updateddate = billRegisterMaster.updateddate != null ? moment(billRegisterMaster.updateddate) : null;
      });
    }
    return res;
  }
}
