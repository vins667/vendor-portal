import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICostingGroupDetails } from 'app/shared/model/costing-group-details.model';
import { ICostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

type EntityResponseType = HttpResponse<ICostingGroupDetails>;
type EntityArrayResponseType = HttpResponse<ICostingGroupDetails[]>;

@Injectable({ providedIn: 'root' })
export class CostingGroupDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/costing-group-details';

  constructor(protected http: HttpClient) {}

  create(costingGroupDetails: ICostingGroupDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingGroupDetails);
    return this.http
      .post<ICostingGroupDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(costingGroupDetails: ICostingGroupDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingGroupDetails);
    return this.http
      .put<ICostingGroupDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICostingGroupDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICostingGroupDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ICostingProcessMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICostingGroupDetails[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(costingGroupDetails: ICostingGroupDetails): ICostingGroupDetails {
    const copy: ICostingGroupDetails = Object.assign({}, costingGroupDetails, {
      createdDate:
        costingGroupDetails.createdDate != null && costingGroupDetails.createdDate.isValid()
          ? costingGroupDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        costingGroupDetails.lastUpdatedDate != null && costingGroupDetails.lastUpdatedDate.isValid()
          ? costingGroupDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((costingGroupDetails: ICostingGroupDetails) => {
        costingGroupDetails.createdDate = costingGroupDetails.createdDate != null ? moment(costingGroupDetails.createdDate) : null;
        costingGroupDetails.lastUpdatedDate =
          costingGroupDetails.lastUpdatedDate != null ? moment(costingGroupDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
