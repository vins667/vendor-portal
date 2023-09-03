import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBankMaster } from 'app/shared/model/bank-master.model';

type EntityResponseType = HttpResponse<IBankMaster>;
type EntityArrayResponseType = HttpResponse<IBankMaster[]>;

@Injectable({ providedIn: 'root' })
export class BankMasterService {
  public resourceUrl = SERVER_API_URL + 'api/bank-masters';

  constructor(protected http: HttpClient) {}

  create(bankMaster: IBankMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankMaster);
    return this.http
      .post<IBankMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bankMaster: IBankMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankMaster);
    return this.http
      .put<IBankMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBankMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBankMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bankMaster: IBankMaster): IBankMaster {
    const copy: IBankMaster = Object.assign({}, bankMaster, {
      createdDate: bankMaster.createdDate != null && bankMaster.createdDate.isValid() ? bankMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        bankMaster.lastUpdatedDate != null && bankMaster.lastUpdatedDate.isValid() ? bankMaster.lastUpdatedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bankMaster: IBankMaster) => {
        bankMaster.createdDate = bankMaster.createdDate != null ? moment(bankMaster.createdDate) : null;
        bankMaster.lastUpdatedDate = bankMaster.lastUpdatedDate != null ? moment(bankMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
