import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBuyerCosting } from 'app/shared/model/buyer-costing.model';
import { IBuyerCostingDetails } from 'app/shared/model/buyer-costing-details.model';
import { IUnitOfMeasure } from 'app/shared/model/unit-of-measure.model';
import { IUserGenericGroup } from 'app/shared/model/user-generic-group.modal';
import { IItemtype } from 'app/shared/model/itemtype.model';

type EntityResponseType = HttpResponse<IBuyerCosting>;
type EntityArrayResponseType = HttpResponse<IBuyerCosting[]>;

@Injectable({ providedIn: 'root' })
export class BuyerCostingService {
  public resourceUrl = SERVER_API_URL + 'api/buyer-costings';

  constructor(protected http: HttpClient) {}

  create(buyerCosting: IBuyerCosting): Observable<EntityResponseType> {
    return this.http.post<IBuyerCosting>(this.resourceUrl, buyerCosting, { observe: 'response' });
  }

  update(buyerCosting: IBuyerCosting): Observable<EntityResponseType> {
    return this.http.put<IBuyerCosting>(this.resourceUrl, buyerCosting, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBuyerCosting>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBuyerCosting[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryGroupMaster(): Observable<HttpResponse<IBuyerCostingDetails[]>> {
    return this.http.get<IBuyerCostingDetails[]>(this.resourceUrl + '-master', { observe: 'response' });
  }

  queryUnitOfMeasure(): Observable<HttpResponse<IUnitOfMeasure[]>> {
    return this.http.get<IUnitOfMeasure[]>(this.resourceUrl + '-measure', { observe: 'response' });
  }

  queryUserGenericGroup(code: string): Observable<HttpResponse<IUserGenericGroup[]>> {
    return this.http.get<IUserGenericGroup[]>(`${this.resourceUrl + '-generic'}/${code}`, { observe: 'response' });
  }

  queryItemTypeCode(code: string): Observable<HttpResponse<IItemtype>> {
    return this.http.get<IItemtype>(`${this.resourceUrl + '-itemtype'}/${code}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
