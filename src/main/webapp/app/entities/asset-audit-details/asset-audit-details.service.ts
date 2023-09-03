import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';
import { IAssetAuditSearch } from 'app/shared/model/asset-audit-search.model';

type EntityResponseType = HttpResponse<IAssetAuditDetails>;
type EntityArrayResponseType = HttpResponse<IAssetAuditDetails[]>;

@Injectable({ providedIn: 'root' })
export class AssetAuditDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/asset-audit-details';

  constructor(protected http: HttpClient) {}

  create(assetAuditDetails: IAssetAuditDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetAuditDetails);
    return this.http
      .post<IAssetAuditDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetAuditDetails: IAssetAuditDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetAuditDetails);
    return this.http
      .put<IAssetAuditDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetAuditDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findCustom(systemId: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IAssetAuditDetails[]>(`${this.resourceUrl}/${systemId}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetAuditDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(assetAuditSearch?: IAssetAuditSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IAssetAuditDetails[]>(this.resourceUrl + '-custom', assetAuditSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assetAuditDetails: IAssetAuditDetails): IAssetAuditDetails {
    const copy: IAssetAuditDetails = Object.assign({}, assetAuditDetails, {
      osInstallationDate:
        assetAuditDetails.osInstallationDate != null && assetAuditDetails.osInstallationDate.isValid()
          ? assetAuditDetails.osInstallationDate.toJSON()
          : null,
      createdDate:
        assetAuditDetails.createdDate != null && assetAuditDetails.createdDate.isValid() ? assetAuditDetails.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.osInstallationDate = res.body.osInstallationDate != null ? moment(res.body.osInstallationDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((assetAuditDetails: IAssetAuditDetails) => {
        assetAuditDetails.osInstallationDate =
          assetAuditDetails.osInstallationDate != null ? moment(assetAuditDetails.osInstallationDate) : null;
        assetAuditDetails.createdDate = assetAuditDetails.createdDate != null ? moment(assetAuditDetails.createdDate) : null;
      });
    }
    return res;
  }
}
