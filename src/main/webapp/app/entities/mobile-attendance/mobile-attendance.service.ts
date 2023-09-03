import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';

type EntityResponseType = HttpResponse<IMobileAttendance>;
type EntityArrayResponseType = HttpResponse<IMobileAttendance[]>;

@Injectable({ providedIn: 'root' })
export class MobileAttendanceService {
  public resourceUrl = SERVER_API_URL + 'api/mobile-attendances';

  constructor(protected http: HttpClient) {}

  create(mobileAttendance: IMobileAttendance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mobileAttendance);
    return this.http
      .post<IMobileAttendance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(mobileAttendance: IMobileAttendance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mobileAttendance);
    return this.http
      .put<IMobileAttendance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMobileAttendance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMobileAttendance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(mobileAttendance: IMobileAttendance): IMobileAttendance {
    const copy: IMobileAttendance = Object.assign({}, mobileAttendance, {
      attendanceDate:
        mobileAttendance.attendanceDate != null && mobileAttendance.attendanceDate.isValid()
          ? mobileAttendance.attendanceDate.toJSON()
          : null,
      createdDate:
        mobileAttendance.createdDate != null && mobileAttendance.createdDate.isValid() ? mobileAttendance.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.attendanceDate = res.body.attendanceDate != null ? moment(res.body.attendanceDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((mobileAttendance: IMobileAttendance) => {
        mobileAttendance.attendanceDate = mobileAttendance.attendanceDate != null ? moment(mobileAttendance.attendanceDate) : null;
        mobileAttendance.createdDate = mobileAttendance.createdDate != null ? moment(mobileAttendance.createdDate) : null;
      });
    }
    return res;
  }
}
