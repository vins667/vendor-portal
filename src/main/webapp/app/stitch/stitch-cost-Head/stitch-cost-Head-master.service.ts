import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IStitchCostHeadMasterBean } from 'app/shared/model/stitch-cost-head-master-bean.model';

type EntityResponseType = HttpResponse<IStitchCostHeadMasterBean>;

@Injectable({ providedIn: 'root' })
export class StitchCostHeadMasterService {
  public resourceUrl = SERVER_API_URL + 'api/stitch-cost-head-masters';

  constructor(protected http: HttpClient) { }

  update(masterBean: IStitchCostHeadMasterBean): Observable<EntityResponseType> {
    return this.http.put<IStitchCostHeadMasterBean>(this.resourceUrl + '-update', masterBean, { observe: 'response' });
  }

  query(factory: string): Observable<HttpResponse<IStitchCostHeadMasterBean>> {
    return this.http.get<IStitchCostHeadMasterBean>(`${this.resourceUrl}/${factory}`, { observe: 'response' });
  }
}
