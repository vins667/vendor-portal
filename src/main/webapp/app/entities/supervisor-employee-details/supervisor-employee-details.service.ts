import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ISupervisorEmployeeDetails } from 'app/shared/model/supervisor-employee-details.model';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<ISupervisorEmployeeDetails>;
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;

@Injectable({ providedIn: 'root' })
export class SupervisorEmployeeDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/supervisor-employee-details';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEmployeeView[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAttendance(cardNo: string, monthYear: string): Observable<EntityResponseType> {
    return this.http
      .get<ISupervisorEmployeeDetails>(this.resourceUrl + '-attendace/' + cardNo + '/' + monthYear, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      if (res.body.attendanceList) {
        res.body.attendanceList.forEach(attendance => {
          attendance.attendanceDate = attendance.attendanceDate != null ? moment(attendance.attendanceDate) : null;
        });
      }
    }
    return res;
  }
}
