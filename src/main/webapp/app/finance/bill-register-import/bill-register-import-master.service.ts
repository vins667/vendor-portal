import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBillRegisterImportMaster } from './bill-register-import-master.model';
import { IBillRegisterImportSearch } from 'app/finance/bill-register-import/bill-register-import-search.model';
import { IPlantinvoiceSearch } from 'app/finance/bill-register/plantinvoice-search.model';
import { IBuyerRegisterPurchaseLineBeanModel } from './buyer-register-purchase-line-bean.model';

type EntityResponseType = HttpResponse<IBillRegisterImportMaster>;
type EntityArrayResponseType = HttpResponse<IBillRegisterImportMaster[]>;

@Injectable({ providedIn: 'root' })
export class BillRegisterImportMasterService {
  public resourceUrl = SERVER_API_URL + 'api/bill-register-imports';
  public resourceUrlReceived = SERVER_API_URL + 'api/bill-register-import-received';
  public resourceUrlDetails = SERVER_API_URL + 'api/bill-register-import-details';
  public resourceUrlPlantinvoices = SERVER_API_URL + 'api/plantinvoices/';
  public resourceUrlFilter = SERVER_API_URL + 'api/bill-register-imports-filter';
  public resourceUrlSupplier = SERVER_API_URL + 'api/vieworderpartners/';
  public resourceUrlPurchaseorderline = SERVER_API_URL + 'api/bill-register-imports-supplier-filter';

  constructor(protected http: HttpClient) {}

  create(billRegisterMaster: IBillRegisterImportMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegisterMaster);
    return this.http
      .post<IBillRegisterImportMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findBySupplierCode(search: IPlantinvoiceSearch): Observable<HttpResponse<IBuyerRegisterPurchaseLineBeanModel[]>> {
    return this.http.post<IBuyerRegisterPurchaseLineBeanModel[]>(`${this.resourceUrlPurchaseorderline}`, search, { observe: 'response' });
  }

  update(id: number, billRegisterMaster: IBillRegisterImportMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billRegisterMaster);
    return this.http
      .put<IBillRegisterImportMaster>(`${this.resourceUrl}/${id}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  submit(billRegisterMasters: IBillRegisterImportMaster[]): Observable<EntityArrayResponseType> {
    return this.http.post<IBillRegisterImportMaster[]>(`${this.resourceUrlReceived}`, billRegisterMasters, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillRegisterImportMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillRegisterImportMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IBillRegisterImportSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IBillRegisterImportMaster[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadXls(search: IBillRegisterImportSearch): Observable<Blob> {
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

  protected convertDateFromClient(billRegisterMaster: IBillRegisterImportMaster): IBillRegisterImportMaster {
    const copy: IBillRegisterImportMaster = Object.assign({}, billRegisterMaster, {
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
      res.body.forEach((billRegisterMaster: IBillRegisterImportMaster) => {
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
