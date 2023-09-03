import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICostingEfficiencyMaste } from 'app/shared/model/costing-efficiency-maste.model';
import { ICostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

type EntityResponseType = HttpResponse<ICostingEfficiencyMaste>;
type EntityArrayResponseType = HttpResponse<ICostingEfficiencyMaste[]>;

@Injectable({ providedIn: 'root' })
export class CostingEfficiencyMasteService {
  public resourceUrl = SERVER_API_URL + 'api/costing-efficiency-masters';

  constructor(protected http: HttpClient) {}

  create(costingEfficiencyMaste: ICostingEfficiencyMaste): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingEfficiencyMaste);
    return this.http
      .post<ICostingEfficiencyMaste>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(costingEfficiencyMaste: ICostingEfficiencyMaste): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingEfficiencyMaste);
    return this.http
      .put<ICostingEfficiencyMaste>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICostingEfficiencyMaste>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICostingEfficiencyMaste[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ICostingProcessMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICostingEfficiencyMaste[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(costingEfficiencyMaste: ICostingEfficiencyMaste): ICostingEfficiencyMaste {
    const copy: ICostingEfficiencyMaste = Object.assign({}, costingEfficiencyMaste, {
      createddate:
        costingEfficiencyMaste.createddate != null && costingEfficiencyMaste.createddate.isValid()
          ? costingEfficiencyMaste.createddate.toJSON()
          : null,
      updateddate:
        costingEfficiencyMaste.updateddate != null && costingEfficiencyMaste.updateddate.isValid()
          ? costingEfficiencyMaste.updateddate.toJSON()
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
      res.body.forEach((costingEfficiencyMaste: ICostingEfficiencyMaste) => {
        costingEfficiencyMaste.createddate = costingEfficiencyMaste.createddate != null ? moment(costingEfficiencyMaste.createddate) : null;
        costingEfficiencyMaste.updateddate = costingEfficiencyMaste.updateddate != null ? moment(costingEfficiencyMaste.updateddate) : null;
      });
    }
    return res;
  }
}
