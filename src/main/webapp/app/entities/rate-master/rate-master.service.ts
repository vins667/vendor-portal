import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IRateMaster } from 'app/shared/model/rate-master.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<IRateMaster>;
type EntityArrayResponseType = HttpResponse<IRateMaster[]>;

@Injectable({ providedIn: 'root' })
export class RateMasterService {
  public resourceUrl = SERVER_API_URL + 'api/rate-masters';

  constructor(protected http: HttpClient) {}

  create(rateMaster: IRateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rateMaster);
    return this.http
      .post<IRateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rateMaster: IRateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rateMaster);
    return this.http
      .put<IRateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRateMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  CustQuery(vehicleType: string): Observable<EntityResponseType> {
    return this.http
      .get<IRateMaster>(`${this.resourceUrl + '-qry'}/${vehicleType}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRateMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rateMaster: IRateMaster): IRateMaster {
    const copy: IRateMaster = Object.assign({}, rateMaster, {
      startDate: rateMaster.startDate != null && rateMaster.startDate.isValid() ? rateMaster.startDate.toJSON() : null,
      endDate: rateMaster.endDate != null && rateMaster.endDate.isValid() ? rateMaster.endDate.toJSON() : null,
      createdDate: rateMaster.createdDate != null && rateMaster.createdDate.isValid() ? rateMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        rateMaster.lastUpdatedDate != null && rateMaster.lastUpdatedDate.isValid() ? rateMaster.lastUpdatedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
      res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rateMaster: IRateMaster) => {
        rateMaster.startDate = rateMaster.startDate != null ? moment(rateMaster.startDate) : null;
        rateMaster.endDate = rateMaster.endDate != null ? moment(rateMaster.endDate) : null;
        rateMaster.createdDate = rateMaster.createdDate != null ? moment(rateMaster.createdDate) : null;
        rateMaster.lastUpdatedDate = rateMaster.lastUpdatedDate != null ? moment(rateMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
