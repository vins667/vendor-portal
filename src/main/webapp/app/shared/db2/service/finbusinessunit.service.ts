import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';

type EntityArrayResponseType = HttpResponse<IFinbusinessunit[]>;
@Injectable({ providedIn: 'root' })
export class FinbusinessunitService {
  public resourceUrl = SERVER_API_URL + 'api/db2-finbusinessunits';

  constructor(protected http: HttpClient) {}

  query(companycode: string): Observable<EntityArrayResponseType> {
    return this.http.get<IFinbusinessunit[]>(`${this.resourceUrl}/${companycode}`, { observe: 'response' });
  }
}
