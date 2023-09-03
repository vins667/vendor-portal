import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';
import { VcutStylePlanUploadSearch } from 'app/shared/model/vcut-style-plan-upload-search.model';
import { Master } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IVcutOperationMaster>;
type EntityArrayResponseType = HttpResponse<IVcutOperationMaster[]>;

@Injectable({ providedIn: 'root' })
export class VcutOperationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-operation-masters';

  constructor(protected http: HttpClient) {}

  create(vcutOperationMaster: IVcutOperationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutOperationMaster);
    return this.http
      .post<IVcutOperationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutOperationMaster: IVcutOperationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutOperationMaster);
    return this.http
      .put<IVcutOperationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutOperationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  style(id: string): Observable<EntityResponseType> {
    const master = new Master();
    master.desc = id;
    return this.http.post<IVcutOperationMaster>(`${this.resourceUrl + '-style'}`, master, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutOperationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: VcutStylePlanUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IVcutOperationMaster[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutOperationMaster: IVcutOperationMaster): IVcutOperationMaster {
    const copy: IVcutOperationMaster = Object.assign({}, vcutOperationMaster, {
      createdDate:
        vcutOperationMaster.createdDate != null && vcutOperationMaster.createdDate.isValid()
          ? vcutOperationMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        vcutOperationMaster.lastUpdatedDate != null && vcutOperationMaster.lastUpdatedDate.isValid()
          ? vcutOperationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((vcutOperationMaster: IVcutOperationMaster) => {
        vcutOperationMaster.createdDate = vcutOperationMaster.createdDate != null ? moment(vcutOperationMaster.createdDate) : null;
        vcutOperationMaster.lastUpdatedDate =
          vcutOperationMaster.lastUpdatedDate != null ? moment(vcutOperationMaster.lastUpdatedDate) : null;
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
      .post<IVcutOperationMaster>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
}
