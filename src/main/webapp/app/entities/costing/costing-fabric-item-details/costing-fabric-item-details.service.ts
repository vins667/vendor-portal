import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';
import { ICostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

type EntityResponseType = HttpResponse<ICostingFabricItemDetails>;
type EntityArrayResponseType = HttpResponse<ICostingFabricItemDetails[]>;

@Injectable({ providedIn: 'root' })
export class CostingFabricItemDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/costing-fabric-item-details';

  constructor(protected http: HttpClient) {}

  create(costingFabricItemDetails: ICostingFabricItemDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingFabricItemDetails);
    return this.http
      .post<ICostingFabricItemDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(costingFabricItemDetails: ICostingFabricItemDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(costingFabricItemDetails);
    return this.http
      .put<ICostingFabricItemDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICostingFabricItemDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICostingFabricItemDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ICostingProcessMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICostingFabricItemDetails[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(costingFabricItemDetails: ICostingFabricItemDetails): ICostingFabricItemDetails {
    const copy: ICostingFabricItemDetails = Object.assign({}, costingFabricItemDetails, {
      createdDate:
        costingFabricItemDetails.createdDate != null && costingFabricItemDetails.createdDate.isValid()
          ? costingFabricItemDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        costingFabricItemDetails.lastUpdatedDate != null && costingFabricItemDetails.lastUpdatedDate.isValid()
          ? costingFabricItemDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((costingFabricItemDetails: ICostingFabricItemDetails) => {
        costingFabricItemDetails.createdDate =
          costingFabricItemDetails.createdDate != null ? moment(costingFabricItemDetails.createdDate) : null;
        costingFabricItemDetails.lastUpdatedDate =
          costingFabricItemDetails.lastUpdatedDate != null ? moment(costingFabricItemDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
