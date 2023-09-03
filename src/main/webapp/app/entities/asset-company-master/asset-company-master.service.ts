import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetCompanyMaster } from 'app/shared/model/asset-company-master.model';

type EntityResponseType = HttpResponse<IAssetCompanyMaster>;
type EntityArrayResponseType = HttpResponse<IAssetCompanyMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetCompanyMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-company-masters';

  constructor(protected http: HttpClient) {}

  create(assetCompanyMaster: IAssetCompanyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetCompanyMaster);
    return this.http
      .post<IAssetCompanyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetCompanyMaster: IAssetCompanyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetCompanyMaster);
    return this.http
      .put<IAssetCompanyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetCompanyMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetCompanyMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetCompanyMaster: IAssetCompanyMaster): IAssetCompanyMaster {
    const copy: IAssetCompanyMaster = Object.assign({}, assetCompanyMaster, {
      createdDate:
        assetCompanyMaster.createdDate != null && assetCompanyMaster.createdDate.isValid() ? assetCompanyMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        assetCompanyMaster.lastUpdatedDate != null && assetCompanyMaster.lastUpdatedDate.isValid()
          ? assetCompanyMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetCompanyMaster: IAssetCompanyMaster) => {
        assetCompanyMaster.createdDate = assetCompanyMaster.createdDate != null ? moment(assetCompanyMaster.createdDate) : null;
        assetCompanyMaster.lastUpdatedDate = assetCompanyMaster.lastUpdatedDate != null ? moment(assetCompanyMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
