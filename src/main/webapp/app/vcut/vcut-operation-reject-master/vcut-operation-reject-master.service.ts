import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutOperationRejectMaster } from 'app/shared/model/vcut-operation-reject-master.model';

type EntityResponseType = HttpResponse<IVcutOperationRejectMaster>;
type EntityArrayResponseType = HttpResponse<IVcutOperationRejectMaster[]>;

@Injectable({ providedIn: 'root' })
export class VcutOperationRejectMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-operation-reject-masters';

  constructor(protected http: HttpClient) {}

  create(vcutOperationRejectMaster: IVcutOperationRejectMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutOperationRejectMaster);
    return this.http
      .post<IVcutOperationRejectMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutOperationRejectMaster: IVcutOperationRejectMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutOperationRejectMaster);
    return this.http
      .put<IVcutOperationRejectMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutOperationRejectMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutOperationRejectMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutOperationRejectMaster: IVcutOperationRejectMaster): IVcutOperationRejectMaster {
    const copy: IVcutOperationRejectMaster = Object.assign({}, vcutOperationRejectMaster, {
      createdDate:
        vcutOperationRejectMaster.createdDate != null && vcutOperationRejectMaster.createdDate.isValid()
          ? vcutOperationRejectMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        vcutOperationRejectMaster.lastUpdatedDate != null && vcutOperationRejectMaster.lastUpdatedDate.isValid()
          ? vcutOperationRejectMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((vcutOperationRejectMaster: IVcutOperationRejectMaster) => {
        vcutOperationRejectMaster.createdDate =
          vcutOperationRejectMaster.createdDate != null ? moment(vcutOperationRejectMaster.createdDate) : null;
        vcutOperationRejectMaster.lastUpdatedDate =
          vcutOperationRejectMaster.lastUpdatedDate != null ? moment(vcutOperationRejectMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
