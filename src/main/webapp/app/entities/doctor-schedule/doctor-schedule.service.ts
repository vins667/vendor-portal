import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDoctorSchedule } from 'app/shared/model/doctor-schedule.model';

type EntityResponseType = HttpResponse<IDoctorSchedule>;
type EntityArrayResponseType = HttpResponse<IDoctorSchedule[]>;

@Injectable({ providedIn: 'root' })
export class DoctorScheduleService {
  public resourceUrl = SERVER_API_URL + 'api/doctor-schedules';

  constructor(protected http: HttpClient) {}

  create(doctorSchedule: IDoctorSchedule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(doctorSchedule);
    return this.http
      .post<IDoctorSchedule>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(doctorSchedule: IDoctorSchedule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(doctorSchedule);
    return this.http
      .put<IDoctorSchedule>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDoctorSchedule>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  byUnit(): Observable<EntityResponseType> {
    return this.http
      .get<IDoctorSchedule>(`${this.resourceUrl + '-unit'}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDoctorSchedule[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(doctorSchedule: IDoctorSchedule): IDoctorSchedule {
    const copy: IDoctorSchedule = Object.assign({}, doctorSchedule, {
      createdDate: doctorSchedule.createdDate != null && doctorSchedule.createdDate.isValid() ? doctorSchedule.createdDate.toJSON() : null
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
      res.body.forEach((doctorSchedule: IDoctorSchedule) => {
        doctorSchedule.createdDate = doctorSchedule.createdDate != null ? moment(doctorSchedule.createdDate) : null;
      });
    }
    return res;
  }
}
