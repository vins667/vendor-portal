import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IVendors } from 'app/shared/model/vendors.model';
import { IVendorsBean } from 'app/shared/model/vendors-bean.model';
import { IProfileWorkFlow } from 'app/shared/model/profile-work-flow.model';

type EntityResponseType = HttpResponse<IVendors>;
type EntityArrayResponseType = HttpResponse<IVendors[]>;

@Injectable({ providedIn: 'root' })
export class VendorsService {
  public resourceUrl = SERVER_API_URL + 'api/vendors';
  public resourceUrlProfile = SERVER_API_URL + 'api/vendors-profile';
  public resourceUrlWorkFlow = SERVER_API_URL + 'api/profile-work-flows';

  constructor(protected http: HttpClient) {}

  create(vendors: IVendors): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendors);
    return this.http
      .post<IVendors>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendors: IVendorsBean): Observable<HttpResponse<IVendorsBean>> {
    const copy = this.convertDateFromClient(vendors);
    return this.http.put<IVendorsBean>(this.resourceUrl, copy, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendors>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: IVendors): Observable<EntityArrayResponseType> {
    return this.http
      .post<IVendors[]>(this.resourceUrl + '-custom', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  profile(id?: number): Observable<HttpResponse<IVendorsBean>> {
    return this.http.get<IVendorsBean>(`${this.resourceUrlProfile}/${id}`, { observe: 'response' });
  }

  createWorkflow(profileWorkFlow: IProfileWorkFlow): Observable<HttpResponse<IProfileWorkFlow>> {
    return this.http
      .post<IProfileWorkFlow>(this.resourceUrlWorkFlow, profileWorkFlow, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServerWorkFlow(res)));
  }

  protected convertDateFromClient(vendors: IVendors): IVendors {
    const copy: IVendors = Object.assign({}, vendors, {
      requestedDate: vendors.requestedDate != null && vendors.requestedDate.isValid() ? vendors.requestedDate.toJSON() : null,
      approvedDate: vendors.approvedDate != null && vendors.approvedDate.isValid() ? vendors.approvedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.requestedDate = res.body.requestedDate != null ? moment(res.body.requestedDate) : null;
      res.body.approvedDate = res.body.approvedDate != null ? moment(res.body.approvedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vendors: IVendors) => {
        vendors.requestedDate = vendors.requestedDate != null ? moment(vendors.requestedDate) : null;
        vendors.approvedDate = vendors.approvedDate != null ? moment(vendors.approvedDate) : null;
      });
    }
    return res;
  }

  protected convertDateFromServerWorkFlow(res: HttpResponse<IProfileWorkFlow>): HttpResponse<IProfileWorkFlow> {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }
}
