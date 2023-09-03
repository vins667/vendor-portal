import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICostingGroupMaster } from 'app/shared/model/costing-group-master.model';
import { ICostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

type EntityResponseType = HttpResponse<ICostingGroupMaster>;
type EntityArrayResponseType = HttpResponse<ICostingGroupMaster[]>;

@Injectable({ providedIn: 'root' })
export class CostingGroupMasterService {
  public resourceUrl = SERVER_API_URL + 'api/costing-group-masters';

  constructor(protected http: HttpClient) {}

  create(costingGroupMaster: ICostingGroupMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingGroupMaster);
    return this.http
      .post<ICostingGroupMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(costingGroupMaster: ICostingGroupMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingGroupMaster);
    return this.http
      .put<ICostingGroupMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICostingGroupMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICostingGroupMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ICostingProcessMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICostingGroupMaster[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(costingGroupMaster: ICostingGroupMaster): ICostingGroupMaster {
    const copy: ICostingGroupMaster = Object.assign({}, costingGroupMaster, {
      createdDate:
        costingGroupMaster.createdDate != null && costingGroupMaster.createdDate.isValid() ? costingGroupMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        costingGroupMaster.lastUpdatedDate != null && costingGroupMaster.lastUpdatedDate.isValid()
          ? costingGroupMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((costingGroupMaster: ICostingGroupMaster) => {
        costingGroupMaster.createdDate = costingGroupMaster.createdDate != null ? moment(costingGroupMaster.createdDate) : null;
        costingGroupMaster.lastUpdatedDate = costingGroupMaster.lastUpdatedDate != null ? moment(costingGroupMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
