import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDelPlaceMaster } from 'app/shared/model/del-place-master.model';

type EntityResponseType = HttpResponse<IDelPlaceMaster>;
type EntityArrayResponseType = HttpResponse<IDelPlaceMaster[]>;

@Injectable({ providedIn: 'root' })
export class DelPlaceMasterService {
  public resourceUrl = SERVER_API_URL + 'api/del-place-masters';

  constructor(protected http: HttpClient) {}

  create(delPlaceMaster: IDelPlaceMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(delPlaceMaster);
    return this.http
      .post<IDelPlaceMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(delPlaceMaster: IDelPlaceMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(delPlaceMaster);
    return this.http
      .put<IDelPlaceMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDelPlaceMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDelPlaceMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(delPlaceMaster: IDelPlaceMaster): IDelPlaceMaster {
    const copy: IDelPlaceMaster = Object.assign({}, delPlaceMaster, {
      createdDate: delPlaceMaster.createdDate != null && delPlaceMaster.createdDate.isValid() ? delPlaceMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        delPlaceMaster.lastUpdatedDate != null && delPlaceMaster.lastUpdatedDate.isValid() ? delPlaceMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((delPlaceMaster: IDelPlaceMaster) => {
        delPlaceMaster.createdDate = delPlaceMaster.createdDate != null ? moment(delPlaceMaster.createdDate) : null;
        delPlaceMaster.lastUpdatedDate = delPlaceMaster.lastUpdatedDate != null ? moment(delPlaceMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
