import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetTypeMaster } from 'app/shared/model/asset-type-master.model';

type EntityResponseType = HttpResponse<IAssetTypeMaster>;
type EntityArrayResponseType = HttpResponse<IAssetTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-type-masters';

  constructor(protected http: HttpClient) {}

  create(assetTypeMaster: IAssetTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetTypeMaster);
    return this.http
      .post<IAssetTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetTypeMaster: IAssetTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetTypeMaster);
    return this.http
      .put<IAssetTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetTypeMaster: IAssetTypeMaster): IAssetTypeMaster {
    const copy: IAssetTypeMaster = Object.assign({}, assetTypeMaster, {
      createdDate:
        assetTypeMaster.createdDate != null && assetTypeMaster.createdDate.isValid() ? assetTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        assetTypeMaster.lastUpdatedDate != null && assetTypeMaster.lastUpdatedDate.isValid()
          ? assetTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetTypeMaster: IAssetTypeMaster) => {
        assetTypeMaster.createdDate = assetTypeMaster.createdDate != null ? moment(assetTypeMaster.createdDate) : null;
        assetTypeMaster.lastUpdatedDate = assetTypeMaster.lastUpdatedDate != null ? moment(assetTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
