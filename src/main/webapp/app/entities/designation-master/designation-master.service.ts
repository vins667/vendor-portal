import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';

type EntityResponseType = HttpResponse<IDesignationMaster>;
type EntityArrayResponseType = HttpResponse<IDesignationMaster[]>;

@Injectable({ providedIn: 'root' })
export class DesignationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/designation-masters';
  public resourceUrlMs = SERVER_API_URL + 'api/ms-designations';

  constructor(protected http: HttpClient) {}

  create(designationMaster: IDesignationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(designationMaster);
    return this.http
      .post<IDesignationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(designationMaster: IDesignationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(designationMaster);
    return this.http
      .put<IDesignationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDesignationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDesignationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryMs(): Observable<EntityArrayResponseType> {
    return this.http
      .get<IDesignationMaster[]>(this.resourceUrlMs, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(designationMaster: IDesignationMaster): IDesignationMaster {
    const copy: IDesignationMaster = Object.assign({}, designationMaster, {
      createdDate:
        designationMaster.createdDate != null && designationMaster.createdDate.isValid() ? designationMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        designationMaster.lastUpdatedDate != null && designationMaster.lastUpdatedDate.isValid()
          ? designationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((designationMaster: IDesignationMaster) => {
        designationMaster.createdDate = designationMaster.createdDate != null ? moment(designationMaster.createdDate) : null;
        designationMaster.lastUpdatedDate = designationMaster.lastUpdatedDate != null ? moment(designationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
