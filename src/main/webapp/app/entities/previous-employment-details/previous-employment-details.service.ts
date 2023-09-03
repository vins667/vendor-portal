import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

type EntityResponseType = HttpResponse<IPreviousEmploymentDetails>;
type EntityArrayResponseType = HttpResponse<IPreviousEmploymentDetails[]>;

@Injectable({ providedIn: 'root' })
export class PreviousEmploymentDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/previous-employment-details';
  public resourceUrlActive = SERVER_API_URL + 'api/tds-year-masters-active';

  constructor(protected http: HttpClient) {}

  create(previousEmploymentDetails: IPreviousEmploymentDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(previousEmploymentDetails);
    return this.http
      .post<IPreviousEmploymentDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(previousEmploymentDetails: IPreviousEmploymentDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(previousEmploymentDetails);
    return this.http
      .put<IPreviousEmploymentDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPreviousEmploymentDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  active(): Observable<HttpResponse<ITdsYearMaster>> {
    return this.http.get<ITdsYearMaster>(`${this.resourceUrlActive}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPreviousEmploymentDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(previousEmploymentDetails: IPreviousEmploymentDetails): IPreviousEmploymentDetails {
    const copy: IPreviousEmploymentDetails = Object.assign({}, previousEmploymentDetails, {
      dateFrom:
        previousEmploymentDetails.dateFrom != null && previousEmploymentDetails.dateFrom.isValid()
          ? previousEmploymentDetails.dateFrom.toJSON()
          : null,
      dateTo:
        previousEmploymentDetails.dateTo != null && previousEmploymentDetails.dateTo.isValid()
          ? previousEmploymentDetails.dateTo.toJSON()
          : null,
      createdDate:
        previousEmploymentDetails.createdDate != null && previousEmploymentDetails.createdDate.isValid()
          ? previousEmploymentDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        previousEmploymentDetails.lastUpdatedDate != null && previousEmploymentDetails.lastUpdatedDate.isValid()
          ? previousEmploymentDetails.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateFrom = res.body.dateFrom != null ? moment(res.body.dateFrom) : null;
      res.body.dateTo = res.body.dateTo != null ? moment(res.body.dateTo) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((previousEmploymentDetails: IPreviousEmploymentDetails) => {
        previousEmploymentDetails.dateFrom = previousEmploymentDetails.dateFrom != null ? moment(previousEmploymentDetails.dateFrom) : null;
        previousEmploymentDetails.dateTo = previousEmploymentDetails.dateTo != null ? moment(previousEmploymentDetails.dateTo) : null;
        previousEmploymentDetails.createdDate =
          previousEmploymentDetails.createdDate != null ? moment(previousEmploymentDetails.createdDate) : null;
        previousEmploymentDetails.lastUpdatedDate =
          previousEmploymentDetails.lastUpdatedDate != null ? moment(previousEmploymentDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
