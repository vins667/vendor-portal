import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INewsMaster } from 'app/shared/model/news-master.model';

type EntityResponseType = HttpResponse<INewsMaster>;
type EntityArrayResponseType = HttpResponse<INewsMaster[]>;

@Injectable({ providedIn: 'root' })
export class NewsMasterService {
  public resourceUrl = SERVER_API_URL + 'api/news-masters';

  constructor(protected http: HttpClient) {}

  create(newsMaster: INewsMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsMaster);
    return this.http
      .post<INewsMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(newsMaster: INewsMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsMaster);
    return this.http
      .put<INewsMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INewsMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INewsMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(newsMaster: INewsMaster): INewsMaster {
    const copy: INewsMaster = Object.assign({}, newsMaster, {
      createdDate: newsMaster.createdDate != null && newsMaster.createdDate.isValid() ? newsMaster.createdDate.toJSON() : null
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
      res.body.forEach((newsMaster: INewsMaster) => {
        newsMaster.createdDate = newsMaster.createdDate != null ? moment(newsMaster.createdDate) : null;
      });
    }
    return res;
  }
}
