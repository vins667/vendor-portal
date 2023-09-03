import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IVcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutSessionDetails } from 'app/shared/model/vcut-session-details.model';
import { IVcutStylePlanSessionBreakup } from 'app/shared/model/vcut-style-plan-session-breakup.model';

type EntityResponseType = HttpResponse<IVcutSessionMaster>;
type EntityArrayResponseType = HttpResponse<IVcutSessionMaster[]>;

@Injectable({ providedIn: 'root' })
export class VcutSessionMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-session-masters';
  public resourceUrlBreakUp = SERVER_API_URL + 'api/vcut-style-plan-session-breakup';

  constructor(protected http: HttpClient) {}

  create(vcutSessionMaster: IVcutSessionMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutSessionMaster);
    return this.http
      .post<IVcutSessionMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutSessionMaster: IVcutSessionMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutSessionMaster);
    return this.http
      .put<IVcutSessionMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutSessionMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  saveBreakup(vcutSessionDetails?: IVcutSessionDetails[]): Observable<HttpResponse<IVcutStylePlanSessionBreakup[]>> {
    return this.http.post<IVcutStylePlanSessionBreakup[]>(`${this.resourceUrlBreakUp}`, vcutSessionDetails, { observe: 'response' });
  }

  findBreakup(id: number): Observable<HttpResponse<IVcutStylePlanSessionBreakup[]>> {
    return this.http.get<IVcutStylePlanSessionBreakup[]>(`${this.resourceUrlBreakUp}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutSessionMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutSessionMaster: IVcutSessionMaster): IVcutSessionMaster {
    const copy: IVcutSessionMaster = Object.assign({}, vcutSessionMaster, {
      dayStartTime:
        vcutSessionMaster.dayStartTime != null && vcutSessionMaster.dayStartTime.isValid() ? vcutSessionMaster.dayStartTime.toJSON() : null,
      createdDate:
        vcutSessionMaster.createdDate != null && vcutSessionMaster.createdDate.isValid() ? vcutSessionMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        vcutSessionMaster.lastUpdatedDate != null && vcutSessionMaster.lastUpdatedDate.isValid()
          ? vcutSessionMaster.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dayStartTime = res.body.dayStartTime != null ? moment(res.body.dayStartTime) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vcutSessionMaster: IVcutSessionMaster) => {
        vcutSessionMaster.dayStartTime = vcutSessionMaster.dayStartTime != null ? moment(vcutSessionMaster.dayStartTime) : null;
        vcutSessionMaster.createdDate = vcutSessionMaster.createdDate != null ? moment(vcutSessionMaster.createdDate) : null;
        vcutSessionMaster.lastUpdatedDate = vcutSessionMaster.lastUpdatedDate != null ? moment(vcutSessionMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
