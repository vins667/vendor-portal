import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';

type EntityResponseType = HttpResponse<IAssetSoftTypeMaster>;
type EntityArrayResponseType = HttpResponse<IAssetSoftTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetSoftTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-soft-type-masters';

  constructor(protected http: HttpClient) {}

  create(assetSoftTypeMaster: IAssetSoftTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSoftTypeMaster);
    return this.http
      .post<IAssetSoftTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetSoftTypeMaster: IAssetSoftTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSoftTypeMaster);
    return this.http
      .put<IAssetSoftTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetSoftTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetSoftTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetSoftTypeMaster: IAssetSoftTypeMaster): IAssetSoftTypeMaster {
    const copy: IAssetSoftTypeMaster = Object.assign({}, assetSoftTypeMaster, {
      createdDate:
        assetSoftTypeMaster.createdDate != null && assetSoftTypeMaster.createdDate.isValid()
          ? assetSoftTypeMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        assetSoftTypeMaster.lastUpdatedDate != null && assetSoftTypeMaster.lastUpdatedDate.isValid()
          ? assetSoftTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetSoftTypeMaster: IAssetSoftTypeMaster) => {
        assetSoftTypeMaster.createdDate = assetSoftTypeMaster.createdDate != null ? moment(assetSoftTypeMaster.createdDate) : null;
        assetSoftTypeMaster.lastUpdatedDate =
          assetSoftTypeMaster.lastUpdatedDate != null ? moment(assetSoftTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
