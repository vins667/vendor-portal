import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelationMaster } from 'app/shared/model/relation-master.model';

type EntityResponseType = HttpResponse<IRelationMaster>;
type EntityArrayResponseType = HttpResponse<IRelationMaster[]>;

@Injectable({ providedIn: 'root' })
export class RelationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/relation-masters';

  constructor(protected http: HttpClient) {}

  create(relationMaster: IRelationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(relationMaster);
    return this.http
      .post<IRelationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(relationMaster: IRelationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(relationMaster);
    return this.http
      .put<IRelationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRelationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRelationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(relationMaster: IRelationMaster): IRelationMaster {
    const copy: IRelationMaster = Object.assign({}, relationMaster, {
      createdDate: relationMaster.createdDate != null && relationMaster.createdDate.isValid() ? relationMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        relationMaster.lastUpdatedDate != null && relationMaster.lastUpdatedDate.isValid() ? relationMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((relationMaster: IRelationMaster) => {
        relationMaster.createdDate = relationMaster.createdDate != null ? moment(relationMaster.createdDate) : null;
        relationMaster.lastUpdatedDate = relationMaster.lastUpdatedDate != null ? moment(relationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
