import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJobWorkFollowupDetails } from './job-work-followup-details.model';
import { ILeaveTypeMaster } from 'app/shared/model/leave-type-master.model';
import { IEmployeeSearch } from 'app/shared/model/employee-search.model';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IJobWorkFollowup } from 'app/finance/job-work-followup/job-work-followup.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';

type EntityResponseType = HttpResponse<IJobWorkFollowupDetails>;
type EntityArrayResponseType = HttpResponse<IJobWorkFollowupDetails[]>;

@Injectable({ providedIn: 'root' })
export class JobWorkFollowupDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/job-work-followups-details';

  constructor(protected http: HttpClient) {}

  create(jobWorkFollowupDetails: IJobWorkFollowupDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(jobWorkFollowupDetails);
    return this.http
      .post<IJobWorkFollowupDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(jobWorkFollowupDetails: IJobWorkFollowupDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(jobWorkFollowupDetails);
    return this.http
      .put<IJobWorkFollowupDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IJobWorkFollowupDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IJobWorkFollowupDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IJobWorkFollowupDetails[]>(this.resourceUrl + '-filter', search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(jobWorkFollowupDetails: IJobWorkFollowupDetails): IJobWorkFollowupDetails {
    const copy: IJobWorkFollowupDetails = Object.assign({}, jobWorkFollowupDetails, {
      createddate:
        jobWorkFollowupDetails.jobWorkDate != null && jobWorkFollowupDetails.jobWorkDate.isValid()
          ? jobWorkFollowupDetails.jobWorkDate.toJSON()
          : null,
      updateddate:
        jobWorkFollowupDetails.submitDate != null && jobWorkFollowupDetails.submitDate.isValid()
          ? jobWorkFollowupDetails.submitDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.jobWorkDate = res.body.jobWorkDate != null ? moment(res.body.jobWorkDate) : null;
      res.body.submitDate = res.body.submitDate != null ? moment(res.body.submitDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((jobWorkFollowupDetails: IJobWorkFollowupDetails) => {
        jobWorkFollowupDetails.jobWorkDate = jobWorkFollowupDetails.jobWorkDate != null ? moment(jobWorkFollowupDetails.jobWorkDate) : null;
        jobWorkFollowupDetails.submitDate = jobWorkFollowupDetails.submitDate != null ? moment(jobWorkFollowupDetails.submitDate) : null;
      });
    }
    return res;
  }
  findByName(name: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IJobWorkFollowupDetails[]>(`${this.resourceUrl + '-type'}/${name}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
}
