import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

type EntityResponseType = HttpResponse<ITdsYearMaster>;
type EntityArrayResponseType = HttpResponse<ITdsYearMaster[]>;

@Injectable({ providedIn: 'root' })
export class TdsYearMasterService {
  public resourceUrl = SERVER_API_URL + 'api/tds-year-masters';

  constructor(protected http: HttpClient) {}

  create(tdsYearMaster: ITdsYearMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsYearMaster);
    return this.http
      .post<ITdsYearMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tdsYearMaster: ITdsYearMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsYearMaster);
    return this.http
      .put<ITdsYearMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITdsYearMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByYear(year: number): Observable<EntityResponseType> {
    return this.http
      .get<ITdsYearMaster>(`${this.resourceUrl + '-year'}/${year}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITdsYearMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tdsYearMaster: ITdsYearMaster): ITdsYearMaster {
    const copy: ITdsYearMaster = Object.assign({}, tdsYearMaster, {
      createdDate: tdsYearMaster.createdDate != null && tdsYearMaster.createdDate.isValid() ? tdsYearMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tdsYearMaster: ITdsYearMaster) => {
        tdsYearMaster.createdDate = tdsYearMaster.createdDate != null ? moment(tdsYearMaster.createdDate) : null;
      });
    }
    return res;
  }
}
