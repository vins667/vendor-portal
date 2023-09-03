import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendorBuyerAuditLinking } from 'app/shared/model/vendor-buyer-audit-linking.model';
import { IMaster } from 'app/shared/model/master.modal';
import { IVendorMaster } from 'app/shared/model/vendor-master.model';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';

type EntityResponseType = HttpResponse<IVendorBuyerAuditLinking>;
type EntityArrayResponseType = HttpResponse<IVendorBuyerAuditLinking[]>;

@Injectable({ providedIn: 'root' })
export class VendorBuyerAuditLinkingService {
  public resourceUrl = SERVER_API_URL + 'api/vendor-buyer-audit-linkings';
  public resourceUrlSearch = SERVER_API_URL + 'api/vendor-masters-custom-search';
  public resourceUrlBuyer = SERVER_API_URL + 'api/buyer-masters-search';

  constructor(protected http: HttpClient) {}

  create(vendorBuyerAuditLinking: IVendorBuyerAuditLinking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorBuyerAuditLinking);
    return this.http
      .post<IVendorBuyerAuditLinking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendorBuyerAuditLinking: IVendorBuyerAuditLinking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorBuyerAuditLinking);
    return this.http
      .put<IVendorBuyerAuditLinking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendorBuyerAuditLinking>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVendorBuyerAuditLinking[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(masterSearch?: IMaster): Observable<HttpResponse<IVendorMaster[]>> {
    return this.http.post<IVendorMaster[]>(this.resourceUrlSearch, masterSearch, { observe: 'response' });
  }

  searchBuyer(masterSearch?: IMaster): Observable<HttpResponse<IBuyerMaster[]>> {
    return this.http.post<IBuyerMaster[]>(this.resourceUrlBuyer, masterSearch, { observe: 'response' });
  }

  protected convertDateFromClient(vendorBuyerAuditLinking: IVendorBuyerAuditLinking): IVendorBuyerAuditLinking {
    const copy: IVendorBuyerAuditLinking = Object.assign({}, vendorBuyerAuditLinking, {
      createdDate:
        vendorBuyerAuditLinking.createdDate != null && vendorBuyerAuditLinking.createdDate.isValid()
          ? vendorBuyerAuditLinking.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        vendorBuyerAuditLinking.lastUpdatedDate != null && vendorBuyerAuditLinking.lastUpdatedDate.isValid()
          ? vendorBuyerAuditLinking.lastUpdatedDate.toJSON()
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
      res.body.forEach((vendorBuyerAuditLinking: IVendorBuyerAuditLinking) => {
        vendorBuyerAuditLinking.createdDate =
          vendorBuyerAuditLinking.createdDate != null ? moment(vendorBuyerAuditLinking.createdDate) : null;
        vendorBuyerAuditLinking.lastUpdatedDate =
          vendorBuyerAuditLinking.lastUpdatedDate != null ? moment(vendorBuyerAuditLinking.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
