import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHolidayMaster } from 'app/shared/model/holiday-master.model';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<IHolidayMaster>;
type EntityArrayResponseType = HttpResponse<IHolidayMaster[]>;

@Injectable({ providedIn: 'root' })
export class HolidayMasterService {
  public resourceUrl = SERVER_API_URL + 'api/holiday-masters';

  constructor(protected http: HttpClient) {}

  create(holidayMaster: IHolidayMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(holidayMaster);
    return this.http
      .post<IHolidayMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(holidayMaster: IHolidayMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(holidayMaster);
    return this.http
      .put<IHolidayMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHolidayMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHolidayMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(holidayMaster: IHolidayMaster): IHolidayMaster {
    const copy: IHolidayMaster = Object.assign({}, holidayMaster, {
      holidayDate:
        holidayMaster.holidayDate != null && holidayMaster.holidayDate.isValid() ? holidayMaster.holidayDate.format(DATE_FORMAT) : null,
      createdDate: holidayMaster.createdDate != null && holidayMaster.createdDate.isValid() ? holidayMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.holidayDate = res.body.holidayDate != null ? moment(res.body.holidayDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((holidayMaster: IHolidayMaster) => {
        holidayMaster.holidayDate = holidayMaster.holidayDate != null ? moment(holidayMaster.holidayDate) : null;
        holidayMaster.createdDate = holidayMaster.createdDate != null ? moment(holidayMaster.createdDate) : null;
      });
    }
    return res;
  }
}
