import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITermsofshipping } from 'app/shared/db2/model/termsofshipping.model';

type EntityArrayResponseType = HttpResponse<ITermsofshipping[]>;
@Injectable({ providedIn: 'root' })
export class TermsofshippingService {
  public resourceUrl = SERVER_API_URL + 'api/termsofshippings';

  constructor(protected http: HttpClient) {}

  query(): Observable<EntityArrayResponseType> {
    return this.http.get<ITermsofshipping[]>(`${this.resourceUrl}`, { observe: 'response' });
  }
}
