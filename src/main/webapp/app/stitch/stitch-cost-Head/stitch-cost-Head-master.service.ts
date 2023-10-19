import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IStitchCostHeadMasterBean } from 'app/shared/model/stitch-cost-head-master-bean.model';
import { map } from 'rxjs/operators';

type EntityResponseType = HttpResponse<IStitchCostHeadMasterBean>;

@Injectable({ providedIn: 'root' })
export class StitchCostHeadMasterService {
  public resourceUrl = SERVER_API_URL + 'api/stitch-cost-head-details';

  constructor(protected http: HttpClient) { }

  create(masterBean: IStitchCostHeadMasterBean): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(masterBean);
    return this.http
      .post<IStitchCostHeadMasterBean>(this.resourceUrl + '-save', copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(masterBean: IStitchCostHeadMasterBean): Observable<EntityResponseType> {
    return this.http.put<IStitchCostHeadMasterBean>(this.resourceUrl + '-update', masterBean, { observe: 'response' });
  }

  query(factory: string): Observable<HttpResponse<IStitchCostHeadMasterBean>> {
    return this.http.get<IStitchCostHeadMasterBean>(`${this.resourceUrl}/${factory}`, { observe: 'response' });
  }

  protected convertDateFromClient(masterBean: IStitchCostHeadMasterBean): IStitchCostHeadMasterBean {
    const copy: IStitchCostHeadMasterBean = Object.assign({}, masterBean, {
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) { }
    return res;
  }
}
