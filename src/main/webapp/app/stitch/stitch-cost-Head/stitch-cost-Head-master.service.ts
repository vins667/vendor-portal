import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IStitchCostHeadMaster } from 'app/shared/model/stitch-cost-head-master.model';

type EntityResponseType = HttpResponse<IStitchCostHeadMaster>;

@Injectable({ providedIn: 'root' })
export class StitchCostHeadMasterService {
  public resourceUrl = SERVER_API_URL + 'api/stitch-cost-head-masters';

  constructor(protected http: HttpClient) {}

  update(buyerCosting: IStitchCostHeadMaster): Observable<EntityResponseType> {
    return this.http.put<IStitchCostHeadMaster>(this.resourceUrl, buyerCosting, { observe: 'response' });
  }

  queryCostHeadMasters(): Observable<HttpResponse<IStitchCostHeadMaster[]>> {
    return this.http.get<IStitchCostHeadMaster[]>(this.resourceUrl, { observe: 'response' });
  }
}
