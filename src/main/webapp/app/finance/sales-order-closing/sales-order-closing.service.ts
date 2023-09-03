import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, pipe } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISalesOrderClosing } from './sales-order-closing.model';
import { map } from 'rxjs/operators';
import { ISalesOrderClosingSearch, SalesOrderClosingSearch } from 'app/finance/sales-order-closing/sales-order-closing-search.model';

type EntityResponseType = HttpResponse<ISalesOrderClosing>;
type EntityArrayResponseType = HttpResponse<ISalesOrderClosing[]>;

@Injectable({ providedIn: 'root' })
export class SalesOrderClosingService {
  public resourceUrl = SERVER_API_URL + 'api/sales-order-closings';
  public resourceUrlFilter = SERVER_API_URL + 'api/sales-order-closings-filter';
  public projectcode?: string;

  constructor(protected http: HttpClient) {}

  create(salesOrderClosing: ISalesOrderClosing): Observable<EntityResponseType> {
    return this.http.post<ISalesOrderClosing>(this.resourceUrl, salesOrderClosing, { observe: 'response' });
  }

  update(salesOrderClosing: ISalesOrderClosing): Observable<EntityResponseType> {
    return this.http.post<ISalesOrderClosing>(this.resourceUrl + '-save', salesOrderClosing, { observe: 'response' });
  }

  find(projectcode: string): Observable<EntityResponseType> {
    const search = new SalesOrderClosingSearch();
    search.code = projectcode;
    return this.http.post<ISalesOrderClosing>(`${this.resourceUrl}`, search, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISalesOrderClosing[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((salesOrderClosing: ISalesOrderClosing) => {
        salesOrderClosing.projectCode = salesOrderClosing.projectCode != null ? salesOrderClosing.projectCode : null;
        salesOrderClosing.shippedPercentage = salesOrderClosing.shippedPercentage != null ? salesOrderClosing.shippedPercentage : null;
      });
    }
    return res;
  }

  queryFilter(search: ISalesOrderClosingSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ISalesOrderClosing[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
