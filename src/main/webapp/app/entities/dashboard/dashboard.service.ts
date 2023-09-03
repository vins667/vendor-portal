import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IDashboard } from 'app/shared/model/dashboard.model';
import { IWishBean } from 'app/shared/model/wish-bean.model';
import { IMessage } from 'app/shared/model/message.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

type EntityResponseType = HttpResponse<IDashboard>;

@Injectable({ providedIn: 'root' })
export class DashboardService {
  public resourceUrl = SERVER_API_URL + 'api/dashboard';
  public resourceUrlWishes = SERVER_API_URL + 'api/post-wishes';

  constructor(protected http: HttpClient) {}

  query(): Observable<EntityResponseType> {
    return this.http
      .get<IDashboard>(this.resourceUrl, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  calendar(monthYear: string): Observable<EntityResponseType> {
    return this.http
      .get<IDashboard>(this.resourceUrl + '-calendar/' + monthYear, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  postmail(wishBean: IWishBean): Observable<HttpResponse<IMessage>> {
    const copy = wishBean;
    return this.http.post<IMessage>(this.resourceUrlWishes, copy, { observe: 'response' });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      if (res.body.holidayMastersList) {
        res.body.holidayMastersList.forEach(holidayMaster => {
          holidayMaster.holidayDate = holidayMaster.holidayDate != null ? moment(holidayMaster.holidayDate) : null;
        });
      }
      if (res.body.attendanceList) {
        res.body.attendanceList.forEach(attendance => {
          attendance.attendanceDate = attendance.attendanceDate != null ? moment(attendance.attendanceDate) : null;
        });
      }
    }
    return res;
  }
}
