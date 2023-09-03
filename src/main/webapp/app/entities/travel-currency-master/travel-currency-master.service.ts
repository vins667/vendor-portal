import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';

type EntityResponseType = HttpResponse<ITravelCurrencyMaster>;
type EntityArrayResponseType = HttpResponse<ITravelCurrencyMaster[]>;

@Injectable({ providedIn: 'root' })
export class TravelCurrencyMasterService {
  public resourceUrl = SERVER_API_URL + 'api/travel-currency-masters';

  constructor(protected http: HttpClient) {}

  create(travelCurrencyMaster: ITravelCurrencyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelCurrencyMaster);
    return this.http
      .post<ITravelCurrencyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelCurrencyMaster: ITravelCurrencyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelCurrencyMaster);
    return this.http
      .put<ITravelCurrencyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelCurrencyMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  loadCurrencyList(): Observable<EntityArrayResponseType> {
    return this.http
      .get<ITravelCurrencyMaster[]>(`${this.resourceUrl + '-list'}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelCurrencyMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelCurrencyMaster: ITravelCurrencyMaster): ITravelCurrencyMaster {
    const copy: ITravelCurrencyMaster = Object.assign({}, travelCurrencyMaster, {
      createdDate:
        travelCurrencyMaster.createdDate != null && travelCurrencyMaster.createdDate.isValid()
          ? travelCurrencyMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        travelCurrencyMaster.lastUpdatedDate != null && travelCurrencyMaster.lastUpdatedDate.isValid()
          ? travelCurrencyMaster.lastUpdatedDate.toJSON()
          : null
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
      res.body.forEach((travelCurrencyMaster: ITravelCurrencyMaster) => {
        travelCurrencyMaster.createdDate = travelCurrencyMaster.createdDate != null ? moment(travelCurrencyMaster.createdDate) : null;
        travelCurrencyMaster.lastUpdatedDate =
          travelCurrencyMaster.lastUpdatedDate != null ? moment(travelCurrencyMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
