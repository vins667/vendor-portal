import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';

type EntityResponseType = HttpResponse<IAssetOwnershipMaster>;
type EntityArrayResponseType = HttpResponse<IAssetOwnershipMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetOwnershipMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-ownership-masters';

  constructor(protected http: HttpClient) {}

  create(assetOwnershipMaster: IAssetOwnershipMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetOwnershipMaster);
    return this.http
      .post<IAssetOwnershipMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetOwnershipMaster: IAssetOwnershipMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetOwnershipMaster);
    return this.http
      .put<IAssetOwnershipMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetOwnershipMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetOwnershipMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetOwnershipMaster: IAssetOwnershipMaster): IAssetOwnershipMaster {
    const copy: IAssetOwnershipMaster = Object.assign({}, assetOwnershipMaster, {
      createdDate:
        assetOwnershipMaster.createdDate != null && assetOwnershipMaster.createdDate.isValid()
          ? assetOwnershipMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        assetOwnershipMaster.lastUpdatedDate != null && assetOwnershipMaster.lastUpdatedDate.isValid()
          ? assetOwnershipMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetOwnershipMaster: IAssetOwnershipMaster) => {
        assetOwnershipMaster.createdDate = assetOwnershipMaster.createdDate != null ? moment(assetOwnershipMaster.createdDate) : null;
        assetOwnershipMaster.lastUpdatedDate =
          assetOwnershipMaster.lastUpdatedDate != null ? moment(assetOwnershipMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
