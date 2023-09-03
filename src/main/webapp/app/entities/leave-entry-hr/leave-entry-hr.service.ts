import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';
import { IMessage } from 'app/shared/model/message.model';
import { ILeaveSearch } from 'app/shared/model/leave-search.model';
import { ILeaveStatus } from 'app/shared/model/leave-status.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<ILeaveEntryHr>;
type EntityArrayResponseType = HttpResponse<ILeaveEntryHr[]>;

@Injectable({ providedIn: 'root' })
export class LeaveEntryHrService {
  public resourceUrl = SERVER_API_URL + 'api/leave-masters';
  public resourceUrlHr = SERVER_API_URL + 'api/leave-masters-hr-update';
  public resourceUrlStatus = SERVER_API_URL + 'api/leave-balance';

  constructor(protected http: HttpClient) {}

  create(leaveMaster: ILeaveEntryHr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(leaveMaster);
    return this.http
      .post<ILeaveEntryHr>(this.resourceUrl + '-custom', copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(leaveSaveMasters: ILeaveEntryHr[]): Observable<HttpResponse<IMessage>> {
    const leaveMasters = [];
    leaveSaveMasters.forEach(leaveMaster => {
      const copy = this.convertDateFromClient(leaveMaster);
      leaveMasters.push(copy);
    });
    return this.http.put<IMessage>(this.resourceUrl, leaveMasters, { observe: 'response' });
  }

  updateHr(leaveSaveMasters: ILeaveEntryHr[]): Observable<HttpResponse<IMessage>> {
    const leaveMasters = [];
    leaveSaveMasters.forEach(leaveMaster => {
      const copy = this.convertDateFromClient(leaveMaster);
      leaveMasters.push(copy);
    });
    return this.http.put<IMessage>(this.resourceUrlHr, leaveMasters, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILeaveEntryHr>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILeaveEntryHr[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryHod(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<ILeaveEntryHr[]>(`${this.resourceUrl + '-hod'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryHr(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<ILeaveEntryHr[]>(`${this.resourceUrl + '-hr-entry'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadPdf(req?: ILeaveSearch): Observable<Blob> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http.post(`${this.resourceUrl + '-hr-report'}`, copy, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  leaveBalance(master: IMaster): Observable<HttpResponse<ILeaveStatus>> {
    return this.http.post<ILeaveStatus>(`${this.resourceUrlStatus}`, master, { observe: 'response' });
  }

  protected convertDateFromClient(leaveMaster: ILeaveEntryHr): ILeaveEntryHr {
    const copy: ILeaveEntryHr = Object.assign({}, leaveMaster, {
      leaveDateFrom: leaveMaster.leaveDateFrom != null && leaveMaster.leaveDateFrom.isValid() ? leaveMaster.leaveDateFrom.toJSON() : null,
      leaveDateTo: leaveMaster.leaveDateTo != null && leaveMaster.leaveDateTo.isValid() ? leaveMaster.leaveDateTo.toJSON() : null,
      createdDate: leaveMaster.createdDate != null && leaveMaster.createdDate.isValid() ? leaveMaster.createdDate.toJSON() : null,
      hodApprovedDate:
        leaveMaster.hodApprovedDate != null && leaveMaster.hodApprovedDate.isValid() ? leaveMaster.hodApprovedDate.toJSON() : null,
      hrApprovedDate:
        leaveMaster.hrApprovedDate != null && leaveMaster.hrApprovedDate.isValid() ? leaveMaster.hrApprovedDate.toJSON() : null,
      leaveTimeFrom: leaveMaster.leaveTimeFrom != null && leaveMaster.leaveTimeFrom.isValid() ? leaveMaster.leaveTimeFrom.toJSON() : null,
      leaveTimeTo: leaveMaster.leaveTimeTo != null && leaveMaster.leaveTimeTo.isValid() ? leaveMaster.leaveTimeTo.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.leaveDateFrom = res.body.leaveDateFrom != null ? moment(res.body.leaveDateFrom) : null;
      res.body.leaveDateTo = res.body.leaveDateTo != null ? moment(res.body.leaveDateTo) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.hodApprovedDate = res.body.hodApprovedDate != null ? moment(res.body.hodApprovedDate) : null;
      res.body.hrApprovedDate = res.body.hrApprovedDate != null ? moment(res.body.hrApprovedDate) : null;
      res.body.leaveTimeFrom = res.body.leaveTimeFrom != null ? moment(res.body.leaveTimeFrom) : null;
      res.body.leaveTimeTo = res.body.leaveTimeTo != null ? moment(res.body.leaveTimeTo) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((leaveMaster: ILeaveEntryHr) => {
        leaveMaster.leaveDateFrom = leaveMaster.leaveDateFrom != null ? moment(leaveMaster.leaveDateFrom) : null;
        leaveMaster.leaveDateTo = leaveMaster.leaveDateTo != null ? moment(leaveMaster.leaveDateTo) : null;
        leaveMaster.createdDate = leaveMaster.createdDate != null ? moment(leaveMaster.createdDate) : null;
        leaveMaster.hodApprovedDate = leaveMaster.hodApprovedDate != null ? moment(leaveMaster.hodApprovedDate) : null;
        leaveMaster.hrApprovedDate = leaveMaster.hrApprovedDate != null ? moment(leaveMaster.hrApprovedDate) : null;
        leaveMaster.leaveTimeFrom = leaveMaster.leaveTimeFrom != null ? moment(leaveMaster.leaveTimeFrom) : null;
        leaveMaster.leaveTimeTo = leaveMaster.leaveTimeTo != null ? moment(leaveMaster.leaveTimeTo) : null;
      });
    }
    return res;
  }

  protected convertDateFromClientLeave(leaveMaster: ILeaveSearch): ILeaveEntryHr {
    const copy: ILeaveSearch = Object.assign({}, leaveMaster, {
      leaveDateFrom: leaveMaster.leaveDateFrom != null && leaveMaster.leaveDateFrom.isValid() ? leaveMaster.leaveDateFrom.toJSON() : null,
      leaveDateTo: leaveMaster.leaveDateTo != null && leaveMaster.leaveDateTo.isValid() ? leaveMaster.leaveDateTo.toJSON() : null
    });
    return copy;
  }
}
