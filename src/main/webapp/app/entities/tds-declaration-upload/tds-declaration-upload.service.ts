import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

type EntityResponseType = HttpResponse<ITdsDeclarationUpload>;
type EntityArrayResponseType = HttpResponse<ITdsDeclarationUpload[]>;

@Injectable({ providedIn: 'root' })
export class TdsDeclarationUploadService {
  public resourceUrl = SERVER_API_URL + 'api/tds-declaration-uploads';
  public resourceUrlActive = SERVER_API_URL + 'api/tds-year-masters-active';
  public customResourceUrl = SERVER_API_URL + 'api/tds-declaration-uploads-custom';

  constructor(protected http: HttpClient) {}
  create(tdsDeclarationUpload: ITdsDeclarationUpload, file: File[]): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    const files: Array<File> = file;
    if (files !== undefined) {
      for (let i = 0; i < files.length; i++) {
        formData.append('file', files[i]);
      }
      formData.append('cardNo', tdsDeclarationUpload.cardNo);
      formData.append('financialYear', tdsDeclarationUpload.financialYear);
      formData.append('tdsGroupMaster', tdsDeclarationUpload.tdsGroupMaster.id + '');
    }
    return this.http
      .post<ITdsDeclarationUpload>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tdsDeclarationUpload: ITdsDeclarationUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsDeclarationUpload);
    return this.http
      .put<ITdsDeclarationUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITdsDeclarationUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITdsDeclarationUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  customQuery(): Observable<EntityResponseType> {
    return this.http.get<ITdsDeclarationUpload>(`${this.customResourceUrl}`, { observe: 'response' });
  }
  active(): Observable<HttpResponse<ITdsYearMaster>> {
    return this.http.get<ITdsYearMaster>(`${this.resourceUrlActive}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tdsDeclarationUpload: ITdsDeclarationUpload): ITdsDeclarationUpload {
    const copy: ITdsDeclarationUpload = Object.assign({}, tdsDeclarationUpload, {
      createdDate:
        tdsDeclarationUpload.createdDate != null && tdsDeclarationUpload.createdDate.isValid()
          ? tdsDeclarationUpload.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        tdsDeclarationUpload.lastUpdatedDate != null && tdsDeclarationUpload.lastUpdatedDate.isValid()
          ? tdsDeclarationUpload.lastUpdatedDate.toJSON()
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
      res.body.forEach((tdsDeclarationUpload: ITdsDeclarationUpload) => {
        tdsDeclarationUpload.createdDate = tdsDeclarationUpload.createdDate != null ? moment(tdsDeclarationUpload.createdDate) : null;
        tdsDeclarationUpload.lastUpdatedDate =
          tdsDeclarationUpload.lastUpdatedDate != null ? moment(tdsDeclarationUpload.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
