import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IQuotationMaster } from 'app/shared/model/quotation-master.model';

type EntityResponseType = HttpResponse<IQuotationMaster>;
type EntityArrayResponseType = HttpResponse<IQuotationMaster[]>;

@Injectable({ providedIn: 'root' })
export class QuotationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/quotation-masters';

  constructor(protected http: HttpClient) {}

  create(quotationMaster: IQuotationMaster): Observable<EntityResponseType> {
    return this.http.post<IQuotationMaster>(this.resourceUrl, quotationMaster, { observe: 'response' });
  }

  update(quotationMaster: IQuotationMaster): Observable<EntityResponseType> {
    return this.http.put<IQuotationMaster>(this.resourceUrl, quotationMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuotationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuotationMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
