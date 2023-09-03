import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';

type EntityResponseType = HttpResponse<IVcutPlanChangeMaster>;
type EntityArrayResponseType = HttpResponse<IVcutPlanChangeMaster[]>;

@Injectable({ providedIn: 'root' })
export class VcutPlanChangeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-plan-change-masters';

  constructor(protected http: HttpClient) {}

  create(vcutPlanChangeMaster: IVcutPlanChangeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutPlanChangeMaster);
    return this.http
      .post<IVcutPlanChangeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutPlanChangeMaster: IVcutPlanChangeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutPlanChangeMaster);
    return this.http
      .put<IVcutPlanChangeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutPlanChangeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutPlanChangeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutPlanChangeMaster: IVcutPlanChangeMaster): IVcutPlanChangeMaster {
    const copy: IVcutPlanChangeMaster = Object.assign({}, vcutPlanChangeMaster, {
      createdDate:
        vcutPlanChangeMaster.createdDate != null && vcutPlanChangeMaster.createdDate.isValid()
          ? vcutPlanChangeMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        vcutPlanChangeMaster.lastUpdatedDate != null && vcutPlanChangeMaster.lastUpdatedDate.isValid()
          ? vcutPlanChangeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((vcutPlanChangeMaster: IVcutPlanChangeMaster) => {
        vcutPlanChangeMaster.createdDate = vcutPlanChangeMaster.createdDate != null ? moment(vcutPlanChangeMaster.createdDate) : null;
        vcutPlanChangeMaster.lastUpdatedDate =
          vcutPlanChangeMaster.lastUpdatedDate != null ? moment(vcutPlanChangeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
