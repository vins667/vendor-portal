import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssetMaster } from 'app/shared/model/asset-master.model';
import { IAssetFileUploadBean } from 'app/shared/model/asset-file-upload-bean.model';
import { IAssetFileUploadDetails } from 'app/shared/model/asset-file-upload-details.model';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<IAssetMaster>;
type EntityArrayResponseType = HttpResponse<IAssetMaster[]>;

@Injectable({ providedIn: 'root' })
export class AssetMasterService {
  public resourceUrl = SERVER_API_URL + 'api/asset-masters';
  public resourceUrlFileUpload = SERVER_API_URL + 'api/asset-file-upload-masters';
  public resourceUrlFileUploadDetail = SERVER_API_URL + 'api/asset-file-upload-details';

  constructor(protected http: HttpClient) {}

  create(assetMaster: IAssetMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetMaster);
    return this.http
      .post<IAssetMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assetMaster: IAssetMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assetMaster);
    return this.http
      .put<IAssetMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssetMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssetMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  attachment(id: number): Observable<HttpResponse<IAssetFileUploadBean>> {
    return this.http.get<IAssetFileUploadBean>(`${this.resourceUrl + '-attachment'}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlFileUpload}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(assetMaster: IAssetMaster): IAssetMaster {
    const copy: IAssetMaster = Object.assign({}, assetMaster, {
      warrantyDate:
        assetMaster.warrantyDate != null && assetMaster.warrantyDate.isValid() ? assetMaster.warrantyDate.format(DATE_FORMAT) : null,
      invoiceDate:
        assetMaster.invoiceDate != null && assetMaster.invoiceDate.isValid() ? assetMaster.invoiceDate.format(DATE_FORMAT) : null,
      createdDate: assetMaster.createdDate != null && assetMaster.createdDate.isValid() ? assetMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        assetMaster.lastUpdatedDate != null && assetMaster.lastUpdatedDate.isValid() ? assetMaster.lastUpdatedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.warrantyDate = res.body.warrantyDate != null ? moment(res.body.warrantyDate) : null;
      res.body.invoiceDate = res.body.invoiceDate != null ? moment(res.body.invoiceDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((assetMaster: IAssetMaster) => {
        assetMaster.warrantyDate = assetMaster.warrantyDate != null ? moment(assetMaster.warrantyDate) : null;
        assetMaster.invoiceDate = assetMaster.invoiceDate != null ? moment(assetMaster.invoiceDate) : null;
        assetMaster.createdDate = assetMaster.createdDate != null ? moment(assetMaster.createdDate) : null;
        assetMaster.lastUpdatedDate = assetMaster.lastUpdatedDate != null ? moment(assetMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }

  createUpload(
    file: File,
    invoiceNumber: string,
    assetSupplierId: number,
    assetDocumentId: number,
    id: number
  ): Observable<HttpResponse<IAssetFileUploadBean>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('invoiceNumber', invoiceNumber);
    formData.append('assetSupplierId', assetSupplierId + '');
    formData.append('assetDocumentId', assetDocumentId + '');
    formData.append('id', id + '');
    return this.http.post<IAssetFileUploadBean>(this.resourceUrlFileUpload, formData, { observe: 'response' });
  }

  apply(assetFileUploadDetails: IAssetFileUploadDetails): Observable<HttpResponse<IAssetFileUploadBean>> {
    return this.http.post<IAssetFileUploadBean>(this.resourceUrlFileUploadDetail, assetFileUploadDetails, { observe: 'response' });
  }

  deleteDetails(id: number): Observable<HttpResponse<IAssetFileUploadBean>> {
    return this.http.delete<IAssetFileUploadBean>(`${this.resourceUrlFileUploadDetail}/${id}`, { observe: 'response' });
  }
}
