import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';

type EntityResponseType = HttpResponse<IAssetSubTypeDetailMaster>;
type EntityArrayResponseType = HttpResponse<IAssetSubTypeDetailMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetSubTypeDetailMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-sub-type-detail-masters';

  constructor(protected http: HttpClient) {}

  create(assetSubTypeDetailMaster: IAssetSubTypeDetailMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSubTypeDetailMaster);
    return this.http
      .post<IAssetSubTypeDetailMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetSubTypeDetailMaster: IAssetSubTypeDetailMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSubTypeDetailMaster);
    return this.http
      .put<IAssetSubTypeDetailMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetSubTypeDetailMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetSubTypeDetailMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByAssetTypes(id?: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IAssetSubTypeDetailMaster[]>(`${this.resourceUrl + '-asset-types'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(assetSubTypeDetailMaster: IAssetSubTypeDetailMaster): IAssetSubTypeDetailMaster {
    const copy: IAssetSubTypeDetailMaster = Object.assign({}, assetSubTypeDetailMaster, {
      createdDate:
        assetSubTypeDetailMaster.createdDate != null && assetSubTypeDetailMaster.createdDate.isValid()
          ? assetSubTypeDetailMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        assetSubTypeDetailMaster.lastUpdatedDate != null && assetSubTypeDetailMaster.lastUpdatedDate.isValid()
          ? assetSubTypeDetailMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetSubTypeDetailMaster: IAssetSubTypeDetailMaster) => {
        assetSubTypeDetailMaster.createdDate =
          assetSubTypeDetailMaster.createdDate != null ? moment(assetSubTypeDetailMaster.createdDate) : null;
        assetSubTypeDetailMaster.lastUpdatedDate =
          assetSubTypeDetailMaster.lastUpdatedDate != null ? moment(assetSubTypeDetailMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
