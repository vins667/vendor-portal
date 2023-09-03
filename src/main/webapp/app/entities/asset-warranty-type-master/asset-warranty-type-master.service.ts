import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';

type EntityResponseType = HttpResponse<IAssetWarrantyTypeMaster>;
type EntityArrayResponseType = HttpResponse<IAssetWarrantyTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetWarrantyTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-warranty-type-masters';

  constructor(protected http: HttpClient) {}

  create(assetWarrantyTypeMaster: IAssetWarrantyTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetWarrantyTypeMaster);
    return this.http
      .post<IAssetWarrantyTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetWarrantyTypeMaster: IAssetWarrantyTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetWarrantyTypeMaster);
    return this.http
      .put<IAssetWarrantyTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetWarrantyTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetWarrantyTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetWarrantyTypeMaster: IAssetWarrantyTypeMaster): IAssetWarrantyTypeMaster {
    const copy: IAssetWarrantyTypeMaster = Object.assign({}, assetWarrantyTypeMaster, {
      createdDate:
        assetWarrantyTypeMaster.createdDate != null && assetWarrantyTypeMaster.createdDate.isValid()
          ? assetWarrantyTypeMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        assetWarrantyTypeMaster.lastUpdatedDate != null && assetWarrantyTypeMaster.lastUpdatedDate.isValid()
          ? assetWarrantyTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetWarrantyTypeMaster: IAssetWarrantyTypeMaster) => {
        assetWarrantyTypeMaster.createdDate =
          assetWarrantyTypeMaster.createdDate != null ? moment(assetWarrantyTypeMaster.createdDate) : null;
        assetWarrantyTypeMaster.lastUpdatedDate =
          assetWarrantyTypeMaster.lastUpdatedDate != null ? moment(assetWarrantyTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
