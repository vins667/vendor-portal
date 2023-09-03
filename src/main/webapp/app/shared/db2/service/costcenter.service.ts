import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ICostcenter } from 'app/shared/db2/model/costcenter.model';

type EntityArrayResponseType = HttpResponse<ICostcenter[]>;
@Injectable({ providedIn: 'root' })
export class CostcenterService {
  public resourceUrl = SERVER_API_URL + 'api/db2-costcenters';

  constructor(protected http: HttpClient) {}

  query(companycode: string): Observable<EntityArrayResponseType> {
    return this.http.get<ICostcenter[]>(`${this.resourceUrl}/${companycode}`, { observe: 'response' });
  }
}
