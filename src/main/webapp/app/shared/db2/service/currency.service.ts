import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICurrency } from 'app/shared/db2/model/currency.model';

type EntityArrayResponseType = HttpResponse<ICurrency[]>;
@Injectable({ providedIn: 'root' })
export class CurrencyService {
  public resourceUrl = SERVER_API_URL + 'api/db2-currencies';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICurrency[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
