import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IShift } from 'app/shared/model/shift.model';
import { ShiftBean } from 'app/shared/model/shift-bean.model';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<IShift>;

@Injectable({ providedIn: 'root' })
export class ShiftService {
  public resourceUrl = SERVER_API_URL + 'api/shift';

  constructor(protected http: HttpClient) {}

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IShift>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByDate(date: any): Observable<EntityResponseType> {
    const shiftBean = new ShiftBean();
    shiftBean.todate = date != null ? moment(date, DATE_TIME_FORMAT).startOf('day') : null;
    return this.http.post<IShift>(`${this.resourceUrl + '-current'}`, shiftBean, { observe: 'response' });
  }
}
