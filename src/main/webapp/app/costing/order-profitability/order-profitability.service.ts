import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IDiCostananlysis } from 'app/shared/db2/model/di-costanalysis.model';
import { Master } from 'app/shared/model/master.modal';

type EntityArrayResponseType = HttpResponse<IDiCostananlysis[]>;

@Injectable({ providedIn: 'root' })
export class OrderProfitabilityService {
  public resourceUrl = SERVER_API_URL + 'api/di-costanalysis';
  public resourceUrlfetch = SERVER_API_URL + 'api/di-costanalysis-fetch';
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';

  constructor(protected http: HttpClient) {}

  create(diCostAnanlysis: IDiCostananlysis[]): Observable<EntityArrayResponseType> {
    return this.http.post<IDiCostananlysis[]>(this.resourceUrl, diCostAnanlysis, { observe: 'response' });
  }

  find(projectcode: string): Observable<EntityArrayResponseType> {
    const master = new Master();
    master.code = projectcode;
    return this.http.post<IDiCostananlysis[]>(`${this.resourceUrlfetch}`, master, { observe: 'response' });
  }
}
