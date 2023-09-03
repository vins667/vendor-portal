import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITurnoverMaster } from 'app/shared/model/turnover-master.model';

type EntityResponseType = HttpResponse<ITurnoverMaster>;
type EntityArrayResponseType = HttpResponse<ITurnoverMaster[]>;

@Injectable({ providedIn: 'root' })
export class TurnoverMasterService {
  public resourceUrl = SERVER_API_URL + 'api/turnover-masters';

  constructor(protected http: HttpClient) {}

  create(turnoverMaster: ITurnoverMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(turnoverMaster);
    return this.http
      .post<ITurnoverMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(turnoverMaster: ITurnoverMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(turnoverMaster);
    return this.http
      .put<ITurnoverMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITurnoverMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITurnoverMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(turnoverMaster: ITurnoverMaster): ITurnoverMaster {
    const copy: ITurnoverMaster = Object.assign({}, turnoverMaster, {
      createdDate: turnoverMaster.createdDate != null && turnoverMaster.createdDate.isValid() ? turnoverMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        turnoverMaster.lastUpdatedDate != null && turnoverMaster.lastUpdatedDate.isValid() ? turnoverMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((turnoverMaster: ITurnoverMaster) => {
        turnoverMaster.createdDate = turnoverMaster.createdDate != null ? moment(turnoverMaster.createdDate) : null;
        turnoverMaster.lastUpdatedDate = turnoverMaster.lastUpdatedDate != null ? moment(turnoverMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
