import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutOperationIssueMaster } from 'app/shared/model/vcut-operation-issue-master.model';

type EntityResponseType = HttpResponse<IVcutOperationIssueMaster>;
type EntityArrayResponseType = HttpResponse<IVcutOperationIssueMaster[]>;

@Injectable({ providedIn: 'root' })
export class VcutOperationIssueMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-operation-issue-masters';

  constructor(protected http: HttpClient) {}

  create(vcutOperationIssueMaster: IVcutOperationIssueMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutOperationIssueMaster);
    return this.http
      .post<IVcutOperationIssueMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutOperationIssueMaster: IVcutOperationIssueMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutOperationIssueMaster);
    return this.http
      .put<IVcutOperationIssueMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutOperationIssueMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutOperationIssueMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutOperationIssueMaster: IVcutOperationIssueMaster): IVcutOperationIssueMaster {
    const copy: IVcutOperationIssueMaster = Object.assign({}, vcutOperationIssueMaster, {
      createdDate:
        vcutOperationIssueMaster.createdDate != null && vcutOperationIssueMaster.createdDate.isValid()
          ? vcutOperationIssueMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        vcutOperationIssueMaster.lastUpdatedDate != null && vcutOperationIssueMaster.lastUpdatedDate.isValid()
          ? vcutOperationIssueMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((vcutOperationIssueMaster: IVcutOperationIssueMaster) => {
        vcutOperationIssueMaster.createdDate =
          vcutOperationIssueMaster.createdDate != null ? moment(vcutOperationIssueMaster.createdDate) : null;
        vcutOperationIssueMaster.lastUpdatedDate =
          vcutOperationIssueMaster.lastUpdatedDate != null ? moment(vcutOperationIssueMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
