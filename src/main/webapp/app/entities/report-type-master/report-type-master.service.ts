import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { IReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<IReportTypeMaster>;
type EntityArrayResponseType = HttpResponse<IReportTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class ReportTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/report-type-masters';

  constructor(protected http: HttpClient) {}

  create(reportTypeMaster: IReportTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportTypeMaster);
    return this.http
      .post<IReportTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reportTypeMaster: IReportTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportTypeMaster);
    return this.http
      .put<IReportTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReportTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReportTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reportTypeMaster: IReportTypeMaster): IReportTypeMaster {
    const copy: IReportTypeMaster = Object.assign({}, reportTypeMaster, {
      createdDate:
        reportTypeMaster.createdDate != null && reportTypeMaster.createdDate.isValid() ? reportTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        reportTypeMaster.lastUpdatedDate != null && reportTypeMaster.lastUpdatedDate.isValid()
          ? reportTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((reportTypeMaster: IReportTypeMaster) => {
        reportTypeMaster.createdDate = reportTypeMaster.createdDate != null ? moment(reportTypeMaster.createdDate) : null;
        reportTypeMaster.lastUpdatedDate = reportTypeMaster.lastUpdatedDate != null ? moment(reportTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
