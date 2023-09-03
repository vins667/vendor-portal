import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITdsGroupMaster } from 'app/shared/model/tds-group-master.model';

type EntityResponseType = HttpResponse<ITdsGroupMaster>;
type EntityArrayResponseType = HttpResponse<ITdsGroupMaster[]>;

@Injectable({ providedIn: 'root' })
export class TdsGroupMasterService {
  public resourceUrl = SERVER_API_URL + 'api/tds-group-masters';

  constructor(protected http: HttpClient) {}

  create(tdsGroupMaster: ITdsGroupMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsGroupMaster);
    return this.http
      .post<ITdsGroupMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tdsGroupMaster: ITdsGroupMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsGroupMaster);
    return this.http
      .put<ITdsGroupMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITdsGroupMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITdsGroupMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tdsGroupMaster: ITdsGroupMaster): ITdsGroupMaster {
    const copy: ITdsGroupMaster = Object.assign({}, tdsGroupMaster, {
      createdDate: tdsGroupMaster.createdDate != null && tdsGroupMaster.createdDate.isValid() ? tdsGroupMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        tdsGroupMaster.lastUpdatedDate != null && tdsGroupMaster.lastUpdatedDate.isValid() ? tdsGroupMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((tdsGroupMaster: ITdsGroupMaster) => {
        tdsGroupMaster.createdDate = tdsGroupMaster.createdDate != null ? moment(tdsGroupMaster.createdDate) : null;
        tdsGroupMaster.lastUpdatedDate = tdsGroupMaster.lastUpdatedDate != null ? moment(tdsGroupMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
