import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IIgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';

type EntityResponseType = HttpResponse<IIgnoreSoftwareMaster>;
type EntityArrayResponseType = HttpResponse<IIgnoreSoftwareMaster[]>;

@Injectable({ providedIn: 'root' })
export class IgnoreSoftwareMasterService {
  public resourceUrl = SERVER_API_URL + 'api/ignore-software-masters';

  constructor(protected http: HttpClient) {}

  create(ignoreSoftwareMaster: IIgnoreSoftwareMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ignoreSoftwareMaster);
    return this.http
      .post<IIgnoreSoftwareMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ignoreSoftwareMaster: IIgnoreSoftwareMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ignoreSoftwareMaster);
    return this.http
      .put<IIgnoreSoftwareMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IIgnoreSoftwareMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IIgnoreSoftwareMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: IIgnoreSoftwareMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IIgnoreSoftwareMaster[]>(this.resourceUrl + '-custom', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ignoreSoftwareMaster: IIgnoreSoftwareMaster): IIgnoreSoftwareMaster {
    const copy: IIgnoreSoftwareMaster = Object.assign({}, ignoreSoftwareMaster, {
      createdDate:
        ignoreSoftwareMaster.createdDate != null && ignoreSoftwareMaster.createdDate.isValid()
          ? ignoreSoftwareMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        ignoreSoftwareMaster.lastUpdatedDate != null && ignoreSoftwareMaster.lastUpdatedDate.isValid()
          ? ignoreSoftwareMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((ignoreSoftwareMaster: IIgnoreSoftwareMaster) => {
        ignoreSoftwareMaster.createdDate = ignoreSoftwareMaster.createdDate != null ? moment(ignoreSoftwareMaster.createdDate) : null;
        ignoreSoftwareMaster.lastUpdatedDate =
          ignoreSoftwareMaster.lastUpdatedDate != null ? moment(ignoreSoftwareMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
