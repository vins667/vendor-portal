import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IDiCostananlysisAverage } from 'app/shared/db2/model/di-costanalysis-average.model';
import { Master } from 'app/shared/model/master.modal';

type EntityArrayResponseType = HttpResponse<IDiCostananlysisAverage[]>;

@Injectable({ providedIn: 'root' })
export class ItemAvgActualService {
  public resourceUrl = SERVER_API_URL + 'api/di-costanalysis-avg';
  public resourceUrlfetch = SERVER_API_URL + 'api/di-costanalysis-avg-fetch';
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';

  constructor(protected http: HttpClient) {}

  create(dicostananlysisaverage: IDiCostananlysisAverage[]): Observable<EntityArrayResponseType> {
    return this.http.post<IDiCostananlysisAverage[]>(this.resourceUrl, dicostananlysisaverage, { observe: 'response' });
  }

  find(projectcode: string): Observable<EntityArrayResponseType> {
    const master = new Master();
    master.code = projectcode;
    return this.http.post<IDiCostananlysisAverage[]>(`${this.resourceUrlfetch}`, master, { observe: 'response' });
  }
}
