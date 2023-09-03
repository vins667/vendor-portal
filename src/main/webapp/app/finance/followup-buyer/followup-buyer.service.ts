import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFollowupBuyer } from './followup-buyer.model';
import { ILeaveTypeMaster } from 'app/shared/model/leave-type-master.model';
import { IEmployeeSearch } from 'app/shared/model/employee-search.model';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IJobWorkFollowup } from 'app/finance/job-work-followup/job-work-followup.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';

type EntityResponseType = HttpResponse<IFollowupBuyer>;
type EntityArrayResponseType = HttpResponse<IFollowupBuyer[]>;

@Injectable({ providedIn: 'root' })
export class FollowupBuyerService {
  public resourceUrl = SERVER_API_URL + 'api/followup-buyers';

  constructor(protected http: HttpClient) {}

  create(followupBuyer: IFollowupBuyer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(followupBuyer);
    return this.http
      .post<IFollowupBuyer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(followupBuyer: IFollowupBuyer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(followupBuyer);
    return this.http
      .put<IFollowupBuyer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFollowupBuyer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFollowupBuyer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IFollowupBuyer[]>(this.resourceUrl + '-filter', search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(followupBuyer: IFollowupBuyer): IFollowupBuyer {
    const copy: IFollowupBuyer = Object.assign({}, followupBuyer, {
      createddate: followupBuyer.createddate != null && followupBuyer.createddate.isValid() ? followupBuyer.createddate.toJSON() : null,
      updateddate: followupBuyer.updateddate != null && followupBuyer.updateddate.isValid() ? followupBuyer.updateddate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate != null ? moment(res.body.createddate) : null;
      res.body.updateddate = res.body.updateddate != null ? moment(res.body.updateddate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((followupBuyer: IFollowupBuyer) => {
        followupBuyer.createddate = followupBuyer.createddate != null ? moment(followupBuyer.createddate) : null;
        followupBuyer.updateddate = followupBuyer.updateddate != null ? moment(followupBuyer.updateddate) : null;
      });
    }
    return res;
  }
  findByName(name: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IFollowupBuyer[]>(`${this.resourceUrl + '-type'}/${name}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
}
