import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICurrencyMaster } from 'app/shared/model/currency-master.model';

type EntityResponseType = HttpResponse<ICurrencyMaster>;
type EntityArrayResponseType = HttpResponse<ICurrencyMaster[]>;

@Injectable({ providedIn: 'root' })
export class CurrencyMasterService {
  public resourceUrl = SERVER_API_URL + 'api/currency-masters';

  constructor(protected http: HttpClient) {}

  create(currencyMaster: ICurrencyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currencyMaster);
    return this.http
      .post<ICurrencyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(currencyMaster: ICurrencyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currencyMaster);
    return this.http
      .put<ICurrencyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICurrencyMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICurrencyMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(currencyMaster: ICurrencyMaster): ICurrencyMaster {
    const copy: ICurrencyMaster = Object.assign({}, currencyMaster, {
      createdDate: currencyMaster.createdDate != null && currencyMaster.createdDate.isValid() ? currencyMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        currencyMaster.lastUpdatedDate != null && currencyMaster.lastUpdatedDate.isValid() ? currencyMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((currencyMaster: ICurrencyMaster) => {
        currencyMaster.createdDate = currencyMaster.createdDate != null ? moment(currencyMaster.createdDate) : null;
        currencyMaster.lastUpdatedDate = currencyMaster.lastUpdatedDate != null ? moment(currencyMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
