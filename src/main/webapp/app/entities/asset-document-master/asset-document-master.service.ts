import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetDocumentMaster } from 'app/shared/model/asset-document-master.model';

type EntityResponseType = HttpResponse<IAssetDocumentMaster>;
type EntityArrayResponseType = HttpResponse<IAssetDocumentMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetDocumentMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-document-masters';

  constructor(protected http: HttpClient) {}

  create(assetDocumentMaster: IAssetDocumentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetDocumentMaster);
    return this.http
      .post<IAssetDocumentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetDocumentMaster: IAssetDocumentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetDocumentMaster);
    return this.http
      .put<IAssetDocumentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetDocumentMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetDocumentMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetDocumentMaster: IAssetDocumentMaster): IAssetDocumentMaster {
    const copy: IAssetDocumentMaster = Object.assign({}, assetDocumentMaster, {
      createdDate:
        assetDocumentMaster.createdDate != null && assetDocumentMaster.createdDate.isValid()
          ? assetDocumentMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        assetDocumentMaster.lastUpdatedDate != null && assetDocumentMaster.lastUpdatedDate.isValid()
          ? assetDocumentMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((assetDocumentMaster: IAssetDocumentMaster) => {
        assetDocumentMaster.createdDate = assetDocumentMaster.createdDate != null ? moment(assetDocumentMaster.createdDate) : null;
        assetDocumentMaster.lastUpdatedDate =
          assetDocumentMaster.lastUpdatedDate != null ? moment(assetDocumentMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
