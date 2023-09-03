import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IEmployeeInformationUpdateAprv } from 'app/shared/model/employee-information-update-aprv.model';
import { ILeaveSearch } from 'app/shared/model/leave-search.model';
import { IMessage } from 'app/shared/model/message.model';

type EntityResponseType = HttpResponse<IEmployeeInformationUpdateAprv>;
type EntityArrayResponseType = HttpResponse<IEmployeeInformationUpdateAprv[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeInformationUpdateAprvService {
  public resourceUrl = SERVER_API_URL + 'api/employee-information-updates';
  public resourceUrlApproval = SERVER_API_URL + 'api/employee-information-updates-approval';

  constructor(protected http: HttpClient) {}

  create(employeeInformationUpdateAprv: IEmployeeInformationUpdateAprv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(employeeInformationUpdateAprv);
    return this.http
      .post<IEmployeeInformationUpdateAprv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(employeeInformationUpdateAprv: IEmployeeInformationUpdateAprv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(employeeInformationUpdateAprv);
    return this.http
      .put<IEmployeeInformationUpdateAprv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeInformationUpdateAprv>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = this.convertDateFromClientLeave(req);
    return this.http
      .post<IEmployeeInformationUpdateAprv[]>(this.resourceUrl + '-hr', options, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  approval(id?: number, flag?: string): Observable<HttpResponse<IMessage>> {
    return this.http.get<IMessage>(`${this.resourceUrlApproval}/${id}/${flag}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(employeeInformationUpdateAprv: IEmployeeInformationUpdateAprv): IEmployeeInformationUpdateAprv {
    const copy: IEmployeeInformationUpdateAprv = Object.assign({}, employeeInformationUpdateAprv, {
      createdDate:
        employeeInformationUpdateAprv.createdDate != null && employeeInformationUpdateAprv.createdDate.isValid()
          ? employeeInformationUpdateAprv.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        employeeInformationUpdateAprv.lastUpdatedDate != null && employeeInformationUpdateAprv.lastUpdatedDate.isValid()
          ? employeeInformationUpdateAprv.lastUpdatedDate.toJSON()
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
      res.body.forEach((employeeInformationUpdateAprv: IEmployeeInformationUpdateAprv) => {
        employeeInformationUpdateAprv.createdDate =
          employeeInformationUpdateAprv.createdDate != null ? moment(employeeInformationUpdateAprv.createdDate) : null;
        employeeInformationUpdateAprv.lastUpdatedDate =
          employeeInformationUpdateAprv.lastUpdatedDate != null ? moment(employeeInformationUpdateAprv.lastUpdatedDate) : null;
      });
    }
    return res;
  }

  protected convertDateFromClientLeave(leaveMaster: ILeaveSearch): ILeaveSearch {
    const copy: ILeaveSearch = Object.assign({}, leaveMaster, {
      leaveDateFrom: leaveMaster.leaveDateFrom != null && leaveMaster.leaveDateFrom.isValid() ? leaveMaster.leaveDateFrom.toJSON() : null,
      leaveDateTo: leaveMaster.leaveDateTo != null && leaveMaster.leaveDateTo.isValid() ? leaveMaster.leaveDateTo.toJSON() : null
    });
    return copy;
  }
}
