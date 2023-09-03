import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetAuditSoftwareCompare } from 'app/shared/model/asset-audit-software-compare.model';
import { IAssetAuditRunTimes } from 'app/shared/model/asset-audit-run-times.model';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { IAssetCompareBean } from 'app/shared/model/asset-compare-bean.model';

type EntityResponseType = HttpResponse<IAssetAuditSoftwareCompare>;
type EntityArrayResponseType = HttpResponse<IAssetAuditSoftwareCompare[]>;
type EntityArrayResponseTypeRunTime = HttpResponse<IAssetAuditRunTimes[]>;

@Injectable({ providedIn: 'root' })
export class AssetAuditSoftwareCompareService {
  public resourceUrl = SERVER_API_URL + 'api/asset-audit-software-compares';
  public resourceUrlRunTimes = SERVER_API_URL + 'api/asset-audit-run-times';
  public resourceUrlCompare = SERVER_API_URL + 'api/asset-masters-compare';

  constructor(protected http: HttpClient) {}

  create(assetAuditSoftwareCompare: IAssetAuditSoftwareCompare): Observable<EntityResponseType> {
    return this.http.post<IAssetAuditSoftwareCompare>(this.resourceUrl, assetAuditSoftwareCompare, { observe: 'response' });
  }

  update(assetAuditSoftwareCompare: IAssetAuditSoftwareCompare): Observable<EntityResponseType> {
    return this.http.put<IAssetAuditSoftwareCompare>(this.resourceUrl, assetAuditSoftwareCompare, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAssetAuditSoftwareCompare>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAssetAuditSoftwareCompare[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryRunTime(req?: any): Observable<EntityArrayResponseTypeRunTime> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetAuditRunTimes[]>(this.resourceUrlRunTimes, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseTypeRunTime) => this.convertDateArrayFromServer(res)));
  }

  fetchEndTime(req: IAssetAuditRunTimes): Observable<EntityArrayResponseTypeRunTime> {
    return this.http
      .post<IAssetAuditRunTimes[]>(this.resourceUrlRunTimes, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseTypeRunTime) => this.convertDateArrayFromServer(res)));
  }

  search(req: IAssetCompareBean): Observable<HttpResponse<IAssetCompareBean>> {
    return this.http.post<IAssetCompareBean>(this.resourceUrlCompare, req, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseTypeRunTime): EntityArrayResponseTypeRunTime {
    if (res.body) {
      res.body.forEach((assetAuditRunTimes: IAssetAuditRunTimes) => {
        assetAuditRunTimes.runTime = assetAuditRunTimes.runTime != null ? moment(assetAuditRunTimes.runTime) : null;
      });
    }
    return res;
  }
}
