import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJobWorkFollowup } from './job-work-followup.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { IJobWorkFollowupBuyerModel } from 'app/finance/job-work-followup/job-work-followup-buyer.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';
import { IJobWorkFollowupSchedule } from 'app/finance/job-work-followup/job-work-followup-schedule.model';

type EntityResponseType = HttpResponse<IJobWorkFollowup>;
type EntityArrayResponseType = HttpResponse<IJobWorkFollowup[]>;

@Injectable({ providedIn: 'root' })
export class JobWorkFollowupService {
  public resourceUrl = SERVER_API_URL + 'api/job-work-followups';
  public resourceUrlSchedule = SERVER_API_URL + 'api/job-work-followups-schedule';
  public resourceUrlMonth = SERVER_API_URL + 'api/db2-financialmonths';

  constructor(protected http: HttpClient) {}

  create(jobWorkFollowup: IJobWorkFollowup): Observable<EntityResponseType> {
    return this.http.post<IJobWorkFollowup>(this.resourceUrl, jobWorkFollowup, { observe: 'response' });
  }

  update(jobWorkFollowup: IJobWorkFollowup): Observable<EntityResponseType> {
    return this.http.put<IJobWorkFollowup>(this.resourceUrl, jobWorkFollowup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJobWorkFollowup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJobWorkFollowup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryFilter(search: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IJobWorkFollowup[]>(this.resourceUrl + '-filter', search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  fetchMonths(finYear: any, jobWorkFollowupId: any): Observable<HttpResponse<IJobWorkFollowupSchedule>> {
    return this.http.get<IJobWorkFollowupSchedule>(`${this.resourceUrlMonth}/${finYear}/${jobWorkFollowupId}`, { observe: 'response' });
  }

  saveSchedule(jobWorkFollowupSchedule: IJobWorkFollowupSchedule): Observable<HttpResponse<IJobWorkFollowupSchedule>> {
    return this.http.post<IJobWorkFollowupSchedule>(this.resourceUrlSchedule, jobWorkFollowupSchedule, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((jobWorkFollowup: IJobWorkFollowupBuyerModel) => {
        jobWorkFollowup.createdDate = jobWorkFollowup.createdDate != null ? moment(jobWorkFollowup.createdDate) : null;
      });
    }
    return res;
  }
}
