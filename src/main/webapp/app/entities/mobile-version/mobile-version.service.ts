import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMobileVersion } from 'app/shared/model/mobile-version.model';

type EntityResponseType = HttpResponse<IMobileVersion>;
type EntityArrayResponseType = HttpResponse<IMobileVersion[]>;

@Injectable({ providedIn: 'root' })
export class MobileVersionService {
  public resourceUrl = SERVER_API_URL + 'api/mobile-versions';

  constructor(protected http: HttpClient) {}

  create(mobileVersion: IMobileVersion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mobileVersion);
    return this.http
      .post<IMobileVersion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(mobileVersion: IMobileVersion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mobileVersion);
    return this.http
      .put<IMobileVersion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMobileVersion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMobileVersion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(mobileVersion: IMobileVersion): IMobileVersion {
    const copy: IMobileVersion = Object.assign({}, mobileVersion, {
      closedDate: mobileVersion.closedDate != null && mobileVersion.closedDate.isValid() ? mobileVersion.closedDate.toJSON() : null,
      createdDate: mobileVersion.createdDate != null && mobileVersion.createdDate.isValid() ? mobileVersion.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.closedDate = res.body.closedDate != null ? moment(res.body.closedDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((mobileVersion: IMobileVersion) => {
        mobileVersion.closedDate = mobileVersion.closedDate != null ? moment(mobileVersion.closedDate) : null;
        mobileVersion.createdDate = mobileVersion.createdDate != null ? moment(mobileVersion.createdDate) : null;
      });
    }
    return res;
  }
}
