import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITermsofdelivery } from 'app/shared/db2/model/termsofdelivery.model';

type EntityArrayResponseType = HttpResponse<ITermsofdelivery[]>;
@Injectable({ providedIn: 'root' })
export class TermsofdeliveryService {
  public resourceUrl = SERVER_API_URL + 'api/termsofdeliveries';

  constructor(protected http: HttpClient) {}

  query(): Observable<EntityArrayResponseType> {
    return this.http.get<ITermsofdelivery[]>(`${this.resourceUrl}`, { observe: 'response' });
  }
}
