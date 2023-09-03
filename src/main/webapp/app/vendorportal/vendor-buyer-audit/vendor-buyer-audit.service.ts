import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendorBuyerAudit } from 'app/shared/model/vendor-buyer-audit.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { IVendorMaster } from 'app/shared/model/vendor-master.model';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { IVendorBuyerAuditDetailsBean } from 'app/shared/model/vendor-buyer-audit-details-bean.model';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';

type EntityResponseType = HttpResponse<IVendorBuyerAudit>;
type EntityArrayResponseType = HttpResponse<IVendorBuyerAudit[]>;

@Injectable({ providedIn: 'root' })
export class VendorBuyerAuditService {
  public resourceUrl = SERVER_API_URL + 'api/vendor-buyer-audits';
  public resourceUrlVendor = SERVER_API_URL + 'api/vendor-buyer-audits-linking-vendor-code';
  public resourceUrlSearch = SERVER_API_URL + 'api/vendor-masters-custom-search';
  public resourceUrlAudits = SERVER_API_URL + 'api/audit-ques-buyer-mapping-dtls-buyer-code';
  public resourceUrlAuditsDetais = SERVER_API_URL + 'api/audit-ques-buyer-mappings-buyer-code';
  public resourceUrlByAudits = SERVER_API_URL + 'api/audit-ques-buyer-mapping-dtls-by-audits';

  constructor(protected http: HttpClient) {}

  create(vendorBuyerAudit: IVendorBuyerAudit): Observable<EntityResponseType> {
    return this.http.post<IVendorBuyerAudit>(this.resourceUrl, vendorBuyerAudit, { observe: 'response' });
  }

  update(vendorBuyerAudit: IVendorBuyerAudit): Observable<EntityResponseType> {
    return this.http.put<IVendorBuyerAudit>(this.resourceUrl, vendorBuyerAudit, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVendorBuyerAudit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVendorBuyerAudit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(masterSearch?: IMaster): Observable<HttpResponse<IVendorMaster[]>> {
    return this.http.post<IVendorMaster[]>(this.resourceUrlSearch, masterSearch, { observe: 'response' });
  }

  findByVendor(vendorCode: string): Observable<HttpResponse<IBuyerMaster[]>> {
    return this.http.get<IBuyerMaster[]>(`${this.resourceUrlVendor}/${vendorCode}`, { observe: 'response' });
  }

  findByBuyer(buyerCode: string, vendorCode: string): Observable<HttpResponse<IVendorBuyerAuditDetailsBean>> {
    const master = new Master();
    master.id = vendorCode;
    master.desc = buyerCode;
    return this.http.post<IVendorBuyerAuditDetailsBean>(`${this.resourceUrlAudits}`, master, { observe: 'response' });
  }

  findByAudits(buyerCode: string): Observable<HttpResponse<IMaster[]>> {
    const master = new Master();
    master.desc = buyerCode;
    return this.http.post<IMaster[]>(`${this.resourceUrlByAudits}`, master, { observe: 'response' });
  }

  findByMap(id: string, buyerCode: string): Observable<HttpResponse<IAuditQuesBuyerMapping>> {
    const master = new Master();
    master.id = id;
    master.desc = buyerCode;
    return this.http.post<IAuditQuesBuyerMapping>(`${this.resourceUrlAuditsDetais}`, master, { observe: 'response' });
  }
}
