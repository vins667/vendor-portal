import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICostingValueMaster } from 'app/shared/model/costing-value-master.model';
import { ICostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

type EntityResponseType = HttpResponse<ICostingValueMaster>;
type EntityArrayResponseType = HttpResponse<ICostingValueMaster[]>;

@Injectable({ providedIn: 'root' })
export class CostingValueMasterService {
  public resourceUrl = SERVER_API_URL + 'api/costing-value-masters';

  constructor(protected http: HttpClient) {}

  create(costingValueMaster: ICostingValueMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingValueMaster);
    return this.http
      .post<ICostingValueMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(costingValueMaster: ICostingValueMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingValueMaster);
    return this.http
      .put<ICostingValueMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICostingValueMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICostingValueMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ICostingProcessMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICostingValueMaster[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(costingValueMaster: ICostingValueMaster): ICostingValueMaster {
    const copy: ICostingValueMaster = Object.assign({}, costingValueMaster, {
      createddate:
        costingValueMaster.createddate != null && costingValueMaster.createddate.isValid() ? costingValueMaster.createddate.toJSON() : null,
      updateddate:
        costingValueMaster.updateddate != null && costingValueMaster.updateddate.isValid() ? costingValueMaster.updateddate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate != null ? moment(res.body.createddate) : null;
      res.body.updateddate = res.body.updateddate != null ? moment(res.body.updateddate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((costingValueMaster: ICostingValueMaster) => {
        costingValueMaster.createddate = costingValueMaster.createddate != null ? moment(costingValueMaster.createddate) : null;
        costingValueMaster.updateddate = costingValueMaster.updateddate != null ? moment(costingValueMaster.updateddate) : null;
      });
    }
    return res;
  }
}
