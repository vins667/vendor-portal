import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetSupplierMaster } from 'app/shared/model/asset-supplier-master.model';

type EntityResponseType = HttpResponse<IAssetSupplierMaster>;
type EntityArrayResponseType = HttpResponse<IAssetSupplierMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetSupplierMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-supplier-masters';

  constructor(protected http: HttpClient) {}

  create(assetSupplierMaster: IAssetSupplierMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSupplierMaster);
    return this.http
      .post<IAssetSupplierMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetSupplierMaster: IAssetSupplierMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetSupplierMaster);
    return this.http
      .put<IAssetSupplierMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetSupplierMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetSupplierMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetSupplierMaster: IAssetSupplierMaster): IAssetSupplierMaster {
    const copy: IAssetSupplierMaster = Object.assign({}, assetSupplierMaster, {
      lastUpdatedDate:
        assetSupplierMaster.lastUpdatedDate != null && assetSupplierMaster.lastUpdatedDate.isValid()
          ? assetSupplierMaster.lastUpdatedDate.toJSON()
          : null,
      createdDate:
        assetSupplierMaster.createdDate != null && assetSupplierMaster.createdDate.isValid()
          ? assetSupplierMaster.createdDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((assetSupplierMaster: IAssetSupplierMaster) => {
        assetSupplierMaster.lastUpdatedDate =
          assetSupplierMaster.lastUpdatedDate != null ? moment(assetSupplierMaster.lastUpdatedDate) : null;
        assetSupplierMaster.createdDate = assetSupplierMaster.createdDate != null ? moment(assetSupplierMaster.createdDate) : null;
      });
    }
    return res;
  }
}
