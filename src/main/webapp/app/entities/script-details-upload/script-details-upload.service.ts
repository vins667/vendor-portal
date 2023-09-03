import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { IScriptDetailsUpload } from 'app/entities/script-details-upload/script-details-upload.model';
import { ScriptDetailsUploadSearch } from 'app/entities/script-details-upload/script-details-upload-search.model';
type EntityResponseType = HttpResponse<IScriptDetailsUpload>;
type EntityArrayResponseType = HttpResponse<IScriptDetailsUpload[]>;

@Injectable({ providedIn: 'root' })
export class ScriptDetailsUploadService {
  public resourceUrl = SERVER_API_URL + 'api/script-details-uploads';

  constructor(protected http: HttpClient) {}

  create(scriptDetailsUpload: IScriptDetailsUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scriptDetailsUpload);
    return this.http
      .post<IScriptDetailsUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(scriptDetailsUpload: IScriptDetailsUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scriptDetailsUpload);
    return this.http
      .put<IScriptDetailsUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IScriptDetailsUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IScriptDetailsUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(scriptDetailsUpload: IScriptDetailsUpload): IScriptDetailsUpload {
    const copy: IScriptDetailsUpload = Object.assign({}, scriptDetailsUpload, {
      shippingBillDate:
        scriptDetailsUpload.shippingBillDate != null && scriptDetailsUpload.shippingBillDate.isValid()
          ? scriptDetailsUpload.shippingBillDate.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.shippingBillDate = res.body.shippingBillDate != null ? moment(res.body.shippingBillDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((scriptDetailsUpload: IScriptDetailsUpload) => {
        scriptDetailsUpload.shippingBillDate =
          scriptDetailsUpload.shippingBillDate != null ? moment(scriptDetailsUpload.shippingBillDate) : null;
      });
    }
    return res;
  }

  upload(file: File[]): Observable<EntityResponseType> {
    const files: Array<File> = file;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', files[i]);
    }
    return this.http
      .post<IScriptDetailsUpload>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  queryCustom(req?: ScriptDetailsUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IScriptDetailsUpload[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
}
