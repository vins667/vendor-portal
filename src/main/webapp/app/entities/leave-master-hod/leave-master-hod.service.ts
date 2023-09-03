import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';
import { ILeaveStatus } from 'app/shared/model/leave-status.model';
import { IMessage } from 'app/shared/model/message.model';
import { ILeaveSearch } from 'app/shared/model/leave-search.model';
import { ILeaveMasterRemarksDetailsBean } from 'app/shared/model/leave-master-remarks-details-bean.model';
import { ILeaveMasterRemarksDetails } from 'app/shared/model/leave-master-remarks-details.model';

type EntityResponseType = HttpResponse<ILeaveMaster>;
type EntityArrayResponseType = HttpResponse<ILeaveMaster[]>;

@Injectable({ providedIn: 'root' })
export class LeaveMasterHodService {
  public resourceUrl = SERVER_API_URL + 'api/leave-masters';
  public resourceUrlHr = SERVER_API_URL + 'api/leave-masters-hr-update';
  public resourceUrlStatus = SERVER_API_URL + 'api/leave-balance';
  public resourceUrlRemarks = SERVER_API_URL + 'api/leave-master-remarks-details';

  constructor(protected http: HttpClient) {}

  create(leaveMaster: ILeaveMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(leaveMaster);
    return this.http
      .post<ILeaveMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(leaveSaveMasters: ILeaveMaster[]): Observable<HttpResponse<IMessage>> {
    const leaveMasters = [];
    leaveSaveMasters.forEach(leaveMaster => {
      const copy = this.convertDateFromClient(leaveMaster);
      leaveMasters.push(copy);
    });
    return this.http.put<IMessage>(this.resourceUrl, leaveMasters, { observe: 'response' });
  }

  updateHr(leaveSaveMasters: ILeaveMaster[]): Observable<HttpResponse<IMessage>> {
    const leaveMasters = [];
    leaveSaveMasters.forEach(leaveMaster => {
      const copy = this.convertDateFromClient(leaveMaster);
      leaveMasters.push(copy);
    });
    return this.http.put<IMessage>(this.resourceUrlHr, leaveMasters, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILeaveMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILeaveMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryHod(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<ILeaveMaster[]>(`${this.resourceUrl + '-hod'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryHr(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<ILeaveMaster[]>(`${this.resourceUrl + '-hr'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadPdf(req?: ILeaveSearch): Observable<Blob> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http.post(`${this.resourceUrl + '-hr-report'}`, copy, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  workFlow(id: number): Observable<HttpResponse<ILeaveMasterRemarksDetailsBean>> {
    return this.http.get<ILeaveMasterRemarksDetailsBean>(`${this.resourceUrl + '-remarks-details'}/${id}`, { observe: 'response' });
  }

  leaveBalance(leaveType: string): Observable<HttpResponse<ILeaveStatus>> {
    return this.http.get<ILeaveStatus>(`${this.resourceUrlStatus}/${leaveType}`, { observe: 'response' });
  }

  updateRemarks(leaveMasterRemarksDetails: ILeaveMasterRemarksDetails): Observable<HttpResponse<ILeaveMasterRemarksDetailsBean>> {
    const copy = this.convertDateFromClientRemarks(leaveMasterRemarksDetails);
    return this.http.post<ILeaveMasterRemarksDetailsBean>(this.resourceUrlRemarks, copy, { observe: 'response' });
  }

  protected convertDateFromClientRemarks(leaveMasterRemarksDetails: ILeaveMasterRemarksDetails): ILeaveMasterRemarksDetails {
    const copy: ILeaveMasterRemarksDetails = Object.assign({}, leaveMasterRemarksDetails, {
      createdDate:
        leaveMasterRemarksDetails.createdDate != null && leaveMasterRemarksDetails.createdDate.isValid()
          ? leaveMasterRemarksDetails.createdDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromClient(leaveMaster: ILeaveMaster): ILeaveMaster {
    const copy: ILeaveMaster = Object.assign({}, leaveMaster, {
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
      res.body.forEach((leaveMaster: ILeaveMaster) => {
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

  protected convertDateFromClientLeave(leaveMaster: ILeaveSearch): ILeaveMaster {
    const copy: ILeaveSearch = Object.assign({}, leaveMaster, {
      leaveDateFrom: leaveMaster.leaveDateFrom != null && leaveMaster.leaveDateFrom.isValid() ? leaveMaster.leaveDateFrom.toJSON() : null,
      leaveDateTo: leaveMaster.leaveDateTo != null && leaveMaster.leaveDateTo.isValid() ? leaveMaster.leaveDateTo.toJSON() : null
    });
    return copy;
  }
}
