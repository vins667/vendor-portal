import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILeaveTypeMaster } from 'app/shared/model/leave-type-master.model';

type EntityResponseType = HttpResponse<ILeaveTypeMaster>;
type EntityArrayResponseType = HttpResponse<ILeaveTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class LeaveTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/leave-type-masters';

  constructor(protected http: HttpClient) {}

  create(leaveTypeMaster: ILeaveTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(leaveTypeMaster);
    return this.http
      .post<ILeaveTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(leaveTypeMaster: ILeaveTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(leaveTypeMaster);
    return this.http
      .put<ILeaveTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILeaveTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByType(leaveType: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<ILeaveTypeMaster[]>(`${this.resourceUrl + '-type'}/${leaveType}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILeaveTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(leaveTypeMaster: ILeaveTypeMaster): ILeaveTypeMaster {
    const copy: ILeaveTypeMaster = Object.assign({}, leaveTypeMaster, {
      createdDate:
        leaveTypeMaster.createdDate != null && leaveTypeMaster.createdDate.isValid() ? leaveTypeMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((leaveTypeMaster: ILeaveTypeMaster) => {
        leaveTypeMaster.createdDate = leaveTypeMaster.createdDate != null ? moment(leaveTypeMaster.createdDate) : null;
      });
    }
    return res;
  }
}
