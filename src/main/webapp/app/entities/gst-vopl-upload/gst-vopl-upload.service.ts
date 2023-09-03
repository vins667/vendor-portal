import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';

type EntityResponseType = HttpResponse<IGstVoplUpload>;
type EntityArrayResponseType = HttpResponse<IGstVoplUpload[]>;

@Injectable({ providedIn: 'root' })
export class GstVoplUploadService {
  public resourceUrl = SERVER_API_URL + 'api/gst-vopl-uploads';

  constructor(protected http: HttpClient) {}

  create(gstVoplUpload: IGstVoplUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gstVoplUpload);
    return this.http
      .post<IGstVoplUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gstVoplUpload: IGstVoplUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gstVoplUpload);
    return this.http
      .put<IGstVoplUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGstVoplUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGstVoplUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gstVoplUpload: IGstVoplUpload): IGstVoplUpload {
    const copy: IGstVoplUpload = Object.assign({}, gstVoplUpload, {
      vVchdate: gstVoplUpload.vVchdate != null && gstVoplUpload.vVchdate.isValid() ? gstVoplUpload.vVchdate.toJSON() : null,
      vInvoicedate: gstVoplUpload.vInvoicedate != null && gstVoplUpload.vInvoicedate.isValid() ? gstVoplUpload.vInvoicedate.toJSON() : null,
      uploadDate: gstVoplUpload.uploadDate != null && gstVoplUpload.uploadDate.isValid() ? gstVoplUpload.uploadDate.toJSON() : null,
      confirmDate: gstVoplUpload.confirmDate != null && gstVoplUpload.confirmDate.isValid() ? gstVoplUpload.confirmDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.vVchdate = res.body.vVchdate != null ? moment(res.body.vVchdate) : null;
      res.body.vInvoicedate = res.body.vInvoicedate != null ? moment(res.body.vInvoicedate) : null;
      res.body.uploadDate = res.body.uploadDate != null ? moment(res.body.uploadDate) : null;
      res.body.confirmDate = res.body.confirmDate != null ? moment(res.body.confirmDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gstVoplUpload: IGstVoplUpload) => {
        gstVoplUpload.vVchdate = gstVoplUpload.vVchdate != null ? moment(gstVoplUpload.vVchdate) : null;
        gstVoplUpload.vInvoicedate = gstVoplUpload.vInvoicedate != null ? moment(gstVoplUpload.vInvoicedate) : null;
        gstVoplUpload.uploadDate = gstVoplUpload.uploadDate != null ? moment(gstVoplUpload.uploadDate) : null;
        gstVoplUpload.confirmDate = gstVoplUpload.confirmDate != null ? moment(gstVoplUpload.confirmDate) : null;
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
      .post<IGstVoplUpload>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
}
