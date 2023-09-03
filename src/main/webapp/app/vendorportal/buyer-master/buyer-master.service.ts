import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IBuyerMaster>;
type EntityArrayResponseType = HttpResponse<IBuyerMaster[]>;

@Injectable({ providedIn: 'root' })
export class BuyerMasterService {
  public resourceUrl = SERVER_API_URL + 'api/buyer-masters';

  constructor(protected http: HttpClient) {}

  create(buyerMaster: IBuyerMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(buyerMaster);
    return this.http
      .post<IBuyerMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(buyerMaster: IBuyerMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(buyerMaster);
    return this.http
      .put<IBuyerMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBuyerMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBuyerMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(masterSearch?: IMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IBuyerMaster[]>(this.resourceUrl + '-search', masterSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(buyerMaster: IBuyerMaster): IBuyerMaster {
    const copy: IBuyerMaster = Object.assign({}, buyerMaster, {
      createdDate: buyerMaster.createdDate != null && buyerMaster.createdDate.isValid() ? buyerMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        buyerMaster.lastUpdatedDate != null && buyerMaster.lastUpdatedDate.isValid() ? buyerMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((buyerMaster: IBuyerMaster) => {
        buyerMaster.createdDate = buyerMaster.createdDate != null ? moment(buyerMaster.createdDate) : null;
        buyerMaster.lastUpdatedDate = buyerMaster.lastUpdatedDate != null ? moment(buyerMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
