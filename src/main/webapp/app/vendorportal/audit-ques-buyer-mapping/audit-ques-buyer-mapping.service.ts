import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';
import { IAuctionQuestionBuyerMappingBean } from 'app/shared/model/auction-question-buyer-mapping-bean.model';
import {BuyerMap} from 'app/shared/model/buyer-map.model';

type EntityResponseType = HttpResponse<IAuditQuesBuyerMapping>;
type EntityArrayResponseType = HttpResponse<IAuditQuesBuyerMapping[]>;

@Injectable({ providedIn: 'root' })
export class AuditQuesBuyerMappingService {
    public resourceUrl = SERVER_API_URL + 'api/audit-ques-buyer-mappings';

    constructor(protected http: HttpClient) {}

    create(auditQuesBuyerMapping: IAuctionQuestionBuyerMappingBean): Observable<EntityResponseType> {
      auditQuesBuyerMapping.vendorAuditGroupMasterBean.forEach(auditGroupMasterBean => {
        auditGroupMasterBean.vendorAuditQuesDetails.forEach(auditQuesDetails => {
          auditQuesDetails.buyerMasters = [];
          for (const key of auditQuesDetails.buyerMastersMap.keys()) {
            auditQuesDetails.buyerMasters.push(new BuyerMap(key, auditQuesDetails.buyerMastersMap.get(key)));
          }
        });
      });
      return this.http.post<IAuditQuesBuyerMapping>(this.resourceUrl, auditQuesBuyerMapping, {observe: 'response'});
    }

    update(auditQuesBuyerMapping: IAuctionQuestionBuyerMappingBean): Observable<EntityResponseType> {
        return this.http.put<IAuditQuesBuyerMapping>(this.resourceUrl, auditQuesBuyerMapping, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAuditQuesBuyerMapping>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAuditQuesBuyerMapping[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
