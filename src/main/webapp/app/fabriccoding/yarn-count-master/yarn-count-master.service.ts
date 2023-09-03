import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';

type EntityResponseType = HttpResponse<IYarnCountMaster>;
type EntityArrayResponseType = HttpResponse<IYarnCountMaster[]>;

@Injectable({ providedIn: 'root' })
export class YarnCountMasterService {
  public resourceUrl = SERVER_API_URL + 'api/yarn-count-masters';

  constructor(protected http: HttpClient) {}

  create(yarnCountMaster: IYarnCountMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(yarnCountMaster);
    return this.http
      .post<IYarnCountMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(yarnCountMaster: IYarnCountMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(yarnCountMaster);
    return this.http
      .put<IYarnCountMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IYarnCountMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IYarnCountMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(yarnCountMaster: IYarnCountMaster): IYarnCountMaster {
    const copy: IYarnCountMaster = Object.assign({}, yarnCountMaster, {
      createdDate:
        yarnCountMaster.createdDate != null && yarnCountMaster.createdDate.isValid() ? yarnCountMaster.createdDate.toJSON() : null,
      lastCreatedDate:
        yarnCountMaster.lastCreatedDate != null && yarnCountMaster.lastCreatedDate.isValid()
          ? yarnCountMaster.lastCreatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastCreatedDate = res.body.lastCreatedDate != null ? moment(res.body.lastCreatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((yarnCountMaster: IYarnCountMaster) => {
        yarnCountMaster.createdDate = yarnCountMaster.createdDate != null ? moment(yarnCountMaster.createdDate) : null;
        yarnCountMaster.lastCreatedDate = yarnCountMaster.lastCreatedDate != null ? moment(yarnCountMaster.lastCreatedDate) : null;
      });
    }
    return res;
  }
}
