import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IDoctorSchedule } from 'app/shared/model/doctor-schedule.model';
import { IMenuDetail } from 'app/shared/model/menu-detail.model';
import { EmployeeView, IEmployeeView } from 'app/shared/model/employee-view.model';

type EntityResponseType = HttpResponse<IDoctorSchedule>;
type EntityArrayResponseType = HttpResponse<IDoctorSchedule[]>;

@Injectable({ providedIn: 'root' })
export class DoctorScheduleService {
  public resourceUrl = SERVER_API_URL + 'api/doctor-schedules';
  public resourceUrlAccess = SERVER_API_URL + 'api/menu-access-masters-authority';
  public resourceUrlEmployee = SERVER_API_URL + 'api/employee-views';

  constructor(protected http: HttpClient) {}

  byUnit(): Observable<EntityResponseType> {
    return this.http
      .get<IDoctorSchedule>(`${this.resourceUrl + '-unit'}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  access(authorityName?: string): Observable<HttpResponse<IMenuDetail[]>> {
    return this.http.get<IMenuDetail[]>(`${this.resourceUrlAccess}/${authorityName}`, { observe: 'response' });
  }

  find(id: string): Observable<HttpResponse<EmployeeView>> {
    return this.http
      .get<IEmployeeView>(`${this.resourceUrlEmployee}/${id}`, { observe: 'response' })
      .pipe(map((res: HttpResponse<EmployeeView>) => this.convertDateFromServerEmployee(res)));
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateFromServerEmployee(res: HttpResponse<EmployeeView>): HttpResponse<EmployeeView> {
    if (res.body) {
      res.body.doj = res.body.doj != null ? moment(res.body.doj) : null;
      res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
    }
    return res;
  }
}
