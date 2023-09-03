import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICostingProcessMaster } from 'app/shared/model/costing-process-master.model';
import { ICostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

type EntityResponseType = HttpResponse<ICostingProcessMaster>;
type EntityArrayResponseType = HttpResponse<ICostingProcessMaster[]>;

@Injectable({ providedIn: 'root' })
export class CostingProcessMasterService {
  public resourceUrl = SERVER_API_URL + 'api/costing-process-masters';

  constructor(protected http: HttpClient) {}

  create(costingProcessMaster: ICostingProcessMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingProcessMaster);
    return this.http
      .post<ICostingProcessMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(costingProcessMaster: ICostingProcessMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingProcessMaster);
    return this.http
      .put<ICostingProcessMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICostingProcessMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICostingProcessMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findCostingProcessList(): Observable<EntityArrayResponseType> {
    return this.http
      .get<ICostingProcessMaster[]>(this.resourceUrl + '-list', { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ICostingProcessMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICostingProcessMaster[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(costingProcessMaster: ICostingProcessMaster): ICostingProcessMaster {
    const copy: ICostingProcessMaster = Object.assign({}, costingProcessMaster, {
      createddate:
        costingProcessMaster.createddate != null && costingProcessMaster.createddate.isValid()
          ? costingProcessMaster.createddate.toJSON()
          : null,
      updateddate:
        costingProcessMaster.updateddate != null && costingProcessMaster.updateddate.isValid()
          ? costingProcessMaster.updateddate.toJSON()
          : null
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
      res.body.forEach((costingProcessMaster: ICostingProcessMaster) => {
        costingProcessMaster.createddate = costingProcessMaster.createddate != null ? moment(costingProcessMaster.createddate) : null;
        costingProcessMaster.updateddate = costingProcessMaster.updateddate != null ? moment(costingProcessMaster.updateddate) : null;
      });
    }
    return res;
  }
}
