import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGstGovtUpload } from 'app/shared/model/gst-govt-upload.model';

type EntityResponseType = HttpResponse<IGstGovtUpload>;
type EntityArrayResponseType = HttpResponse<IGstGovtUpload[]>;

@Injectable({ providedIn: 'root' })
export class GstGovtUploadService {
  public resourceUrl = SERVER_API_URL + 'api/gst-govt-uploads';

  constructor(protected http: HttpClient) {}

  create(gstGovtUpload: IGstGovtUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gstGovtUpload);
    return this.http
      .post<IGstGovtUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gstGovtUpload: IGstGovtUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gstGovtUpload);
    return this.http
      .put<IGstGovtUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGstGovtUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGstGovtUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gstGovtUpload: IGstGovtUpload): IGstGovtUpload {
    const copy: IGstGovtUpload = Object.assign({}, gstGovtUpload, {
      gInvdate: gstGovtUpload.gInvdate != null && gstGovtUpload.gInvdate.isValid() ? gstGovtUpload.gInvdate.toJSON() : null,
      gMonth: gstGovtUpload.gMonth != null && gstGovtUpload.gMonth.isValid() ? gstGovtUpload.gMonth.format(DATE_FORMAT) : null,
      gConfirmdate: gstGovtUpload.gConfirmdate != null && gstGovtUpload.gConfirmdate.isValid() ? gstGovtUpload.gConfirmdate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.gInvdate = res.body.gInvdate != null ? moment(res.body.gInvdate) : null;
      res.body.gMonth = res.body.gMonth != null ? moment(res.body.gMonth) : null;
      res.body.gConfirmdate = res.body.gConfirmdate != null ? moment(res.body.gConfirmdate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gstGovtUpload: IGstGovtUpload) => {
        gstGovtUpload.gInvdate = gstGovtUpload.gInvdate != null ? moment(gstGovtUpload.gInvdate) : null;
        gstGovtUpload.gMonth = gstGovtUpload.gMonth != null ? moment(gstGovtUpload.gMonth) : null;
        gstGovtUpload.gConfirmdate = gstGovtUpload.gConfirmdate != null ? moment(gstGovtUpload.gConfirmdate) : null;
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
      .post<IGstGovtUpload>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
}
