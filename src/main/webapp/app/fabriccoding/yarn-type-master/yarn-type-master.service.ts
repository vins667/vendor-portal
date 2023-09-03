import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IYarnTypeMaster } from 'app/shared/model/yarn-type-master.model';

type EntityResponseType = HttpResponse<IYarnTypeMaster>;
type EntityArrayResponseType = HttpResponse<IYarnTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class YarnTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/yarn-type-masters';

  constructor(protected http: HttpClient) {}

  create(yarnTypeMaster: IYarnTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(yarnTypeMaster);
    return this.http
      .post<IYarnTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(yarnTypeMaster: IYarnTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(yarnTypeMaster);
    return this.http
      .put<IYarnTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IYarnTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IYarnTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(yarnTypeMaster: IYarnTypeMaster): IYarnTypeMaster {
    const copy: IYarnTypeMaster = Object.assign({}, yarnTypeMaster, {
      createdDate: yarnTypeMaster.createdDate != null && yarnTypeMaster.createdDate.isValid() ? yarnTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        yarnTypeMaster.lastUpdatedDate != null && yarnTypeMaster.lastUpdatedDate.isValid() ? yarnTypeMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((yarnTypeMaster: IYarnTypeMaster) => {
        yarnTypeMaster.createdDate = yarnTypeMaster.createdDate != null ? moment(yarnTypeMaster.createdDate) : null;
        yarnTypeMaster.lastUpdatedDate = yarnTypeMaster.lastUpdatedDate != null ? moment(yarnTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
