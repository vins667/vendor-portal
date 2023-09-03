import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetLocationMaster } from 'app/shared/model/asset-location-master.model';

type EntityResponseType = HttpResponse<IAssetLocationMaster>;
type EntityArrayResponseType = HttpResponse<IAssetLocationMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetLocationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-location-masters';

  constructor(protected http: HttpClient) {}

  create(assetLocationMaster: IAssetLocationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetLocationMaster);
    return this.http
      .post<IAssetLocationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetLocationMaster: IAssetLocationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetLocationMaster);
    return this.http
      .put<IAssetLocationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetLocationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetLocationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetLocationMaster: IAssetLocationMaster): IAssetLocationMaster {
    const copy: IAssetLocationMaster = Object.assign({}, assetLocationMaster, {
      createdDate:
        assetLocationMaster.createdDate != null && assetLocationMaster.createdDate.isValid()
          ? assetLocationMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        assetLocationMaster.lastUpdatedDate != null && assetLocationMaster.lastUpdatedDate.isValid()
          ? assetLocationMaster.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((assetLocationMaster: IAssetLocationMaster) => {
        assetLocationMaster.createdDate = assetLocationMaster.createdDate != null ? moment(assetLocationMaster.createdDate) : null;
        assetLocationMaster.lastUpdatedDate =
          assetLocationMaster.lastUpdatedDate != null ? moment(assetLocationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
