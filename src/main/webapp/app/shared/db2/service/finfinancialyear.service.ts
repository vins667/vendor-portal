import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ICompany } from 'app/shared/db2/model/company.model';
import { IFinfinancialyear } from 'app/shared/db2/model/finfinancialyear.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';
type EntityArrayResponseType = HttpResponse<IFinfinancialyear[]>;
@Injectable({ providedIn: 'root' })
export class FinfinancialyearService {
  public resourceUrl = SERVER_API_URL + 'api/db2-financialyears';
  public resourceUrlStatus = SERVER_API_URL + 'api/db2-financialmonths';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFinfinancialyear[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
  queryMonth(finyearcode?: string): Observable<EntityArrayResponseType> {
    return this.http.get<IFinfinancialyear[]>(`${this.resourceUrlStatus}/${finyearcode}`, { observe: 'response' });
  }
}
