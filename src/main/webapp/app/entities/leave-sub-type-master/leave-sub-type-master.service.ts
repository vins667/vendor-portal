import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILeaveSubTypeMaster } from 'app/shared/model/leave-sub-type-master.model';

type EntityResponseType = HttpResponse<ILeaveSubTypeMaster>;
type EntityArrayResponseType = HttpResponse<ILeaveSubTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class LeaveSubTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/leave-sub-type-masters';

  constructor(protected http: HttpClient) {}

  create(leaveSubTypeMaster: ILeaveSubTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(leaveSubTypeMaster);
    return this.http
      .post<ILeaveSubTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(leaveSubTypeMaster: ILeaveSubTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(leaveSubTypeMaster);
    return this.http
      .put<ILeaveSubTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILeaveSubTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILeaveSubTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryByLeaveType(id: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<ILeaveSubTypeMaster[]>(`${this.resourceUrl + '-by-leave'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(leaveSubTypeMaster: ILeaveSubTypeMaster): ILeaveSubTypeMaster {
    const copy: ILeaveSubTypeMaster = Object.assign({}, leaveSubTypeMaster, {
      createdDate:
        leaveSubTypeMaster.createdDate != null && leaveSubTypeMaster.createdDate.isValid() ? leaveSubTypeMaster.createdDate.toJSON() : null
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
      res.body.forEach((leaveSubTypeMaster: ILeaveSubTypeMaster) => {
        leaveSubTypeMaster.createdDate = leaveSubTypeMaster.createdDate != null ? moment(leaveSubTypeMaster.createdDate) : null;
      });
    }
    return res;
  }
}
