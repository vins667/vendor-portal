import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';

type EntityResponseType = HttpResponse<IAssetSubTypeMaster>;
type EntityArrayResponseType = HttpResponse<IAssetSubTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetSubTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-sub-type-masters';

  constructor(protected http: HttpClient) {}

  create(assetSubTypeMaster: IAssetSubTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSubTypeMaster);
    return this.http
      .post<IAssetSubTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetSubTypeMaster: IAssetSubTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSubTypeMaster);
    return this.http
      .put<IAssetSubTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetSubTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetSubTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  findByTangibility(id?: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IAssetSubTypeMaster[]>(`${this.resourceUrl + '-tangibility'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetSubTypeMaster: IAssetSubTypeMaster): IAssetSubTypeMaster {
    const copy: IAssetSubTypeMaster = Object.assign({}, assetSubTypeMaster, {
      createdDate:
        assetSubTypeMaster.createdDate != null && assetSubTypeMaster.createdDate.isValid() ? assetSubTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        assetSubTypeMaster.lastUpdatedDate != null && assetSubTypeMaster.lastUpdatedDate.isValid()
          ? assetSubTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetSubTypeMaster: IAssetSubTypeMaster) => {
        assetSubTypeMaster.createdDate = assetSubTypeMaster.createdDate != null ? moment(assetSubTypeMaster.createdDate) : null;
        assetSubTypeMaster.lastUpdatedDate = assetSubTypeMaster.lastUpdatedDate != null ? moment(assetSubTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
