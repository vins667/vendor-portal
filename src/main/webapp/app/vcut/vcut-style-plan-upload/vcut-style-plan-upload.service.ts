import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { VcutStylePlanUploadSearch } from 'app/shared/model/vcut-style-plan-upload-search.model';
import { IProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';

type EntityResponseType = HttpResponse<IVcutStylePlanUpload>;
type EntityArrayResponseType = HttpResponse<IVcutStylePlanUpload[]>;

@Injectable({ providedIn: 'root' })
export class VcutStylePlanUploadService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-style-plan-uploads';
  public resourceUrlOrders = SERVER_API_URL + 'api/productionorders-stitch';

  constructor(protected http: HttpClient) {}

  create(vcutStylePlanUpload: IVcutStylePlanUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutStylePlanUpload);
    return this.http
      .post<IVcutStylePlanUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutStylePlanUpload: IVcutStylePlanUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutStylePlanUpload);
    return this.http
      .put<IVcutStylePlanUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutStylePlanUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutStylePlanUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustomPO(search: IProductionorderSearch): Observable<HttpResponse<IProductionorder[]>> {
    return this.http.post<IProductionorder[]>(this.resourceUrlOrders, search, { observe: 'response' });
  }

  queryCustom(req?: VcutStylePlanUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IVcutStylePlanUpload[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutStylePlanUpload: IVcutStylePlanUpload): IVcutStylePlanUpload {
    const copy: IVcutStylePlanUpload = Object.assign({}, vcutStylePlanUpload, {
      planDate:
        vcutStylePlanUpload.planDate != null && vcutStylePlanUpload.planDate.isValid()
          ? vcutStylePlanUpload.planDate.format(DATE_FORMAT)
          : null,
      createdDate:
        vcutStylePlanUpload.createdDate != null && vcutStylePlanUpload.createdDate.isValid()
          ? vcutStylePlanUpload.createdDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.planDate = res.body.planDate != null ? moment(res.body.planDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vcutStylePlanUpload: IVcutStylePlanUpload) => {
        vcutStylePlanUpload.planDate = vcutStylePlanUpload.planDate != null ? moment(vcutStylePlanUpload.planDate) : null;
        vcutStylePlanUpload.createdDate = vcutStylePlanUpload.createdDate != null ? moment(vcutStylePlanUpload.createdDate) : null;
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
      .post<IVcutStylePlanUpload>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
}
