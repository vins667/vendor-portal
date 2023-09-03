import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { IDirectBookingSearch } from 'app/shared/model/direct-booking-search.model';
import { IDateBean } from 'app/shared/model/date-bean.model';

type EntityResponseType = HttpResponse<IDirectBookingEntry>;
type EntityArrayResponseType = HttpResponse<IDirectBookingEntry[]>;

@Injectable({ providedIn: 'root' })
export class DirectBookingEntryService {
  public resourceUrl = SERVER_API_URL + 'api/direct-booking-entries';
  public resourceUrlCopy = SERVER_API_URL + 'api/direct-booking-entries-copy';
  public resourceUrlDetails = SERVER_API_URL + 'api/direct-booking-details';
  public resourceUrlForward = SERVER_API_URL + 'api/direct-booking-entries-forward';
  public resourceUrlFilter = SERVER_API_URL + 'api/direct-booking-entries-filter';
  public resourceUrlSupplier = SERVER_API_URL + 'api/vieworderpartners/';
  public resourceUrlCostcenter = SERVER_API_URL + 'api/costcenters/';
  public resourceUrlGlmaster = SERVER_API_URL + 'api/glmasters/';
  public resourceUrlPaymentmethod = SERVER_API_URL + 'api/paymentmethods/';
  public resourceUrlItems = SERVER_API_URL + 'api/fullitemkeydecoders/';
  public resourceUrlGST = SERVER_API_URL + 'api/viewditaxglmappings/';
  public resourceUrlCurrentDate = SERVER_API_URL + 'api/direct-current-date';
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';
  public resourceUrlMainEntry = SERVER_API_URL + 'api/gateentries/';
  public resourceUrlPassNo = SERVER_API_URL + 'api/gateentries-by-passno/';

  constructor(protected http: HttpClient) {}

  create(directBookingEntry: IDirectBookingEntry): Observable<EntityResponseType> {
    return this.http
      .post<IDirectBookingEntry>(this.resourceUrl, directBookingEntry, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(directBookingEntry: IDirectBookingEntry): Observable<EntityResponseType> {
    return this.http
      .put<IDirectBookingEntry>(this.resourceUrl, directBookingEntry, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDirectBookingEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  copy(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDirectBookingEntry>(`${this.resourceUrlCopy}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  currentDate(): Observable<HttpResponse<IDateBean>> {
    return this.http.get<IDateBean>(`${this.resourceUrlCurrentDate}`, { observe: 'response' });
  }

  forward(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDirectBookingEntry>(`${this.resourceUrlForward}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDirectBookingEntry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryFilter(search: IDirectBookingSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IDirectBookingEntry[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadXlsx(req?: IDirectBookingSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrlFilter + '-report'}`, req, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetails(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.bookingdate = res.body.bookingdate ? moment(res.body.bookingdate) : undefined;
      res.body.billdate = res.body.billdate ? moment(res.body.billdate) : undefined;
      res.body.createddate = res.body.createddate ? moment(res.body.createddate) : undefined;
      res.body.updateddate = res.body.updateddate ? moment(res.body.updateddate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((directBookingEntry: IDirectBookingEntry) => {
        directBookingEntry.bookingdate = directBookingEntry.bookingdate ? moment(directBookingEntry.bookingdate) : undefined;
        directBookingEntry.billdate = directBookingEntry.billdate ? moment(directBookingEntry.billdate) : undefined;
        directBookingEntry.createddate = directBookingEntry.createddate ? moment(directBookingEntry.createddate) : undefined;
        directBookingEntry.updateddate = directBookingEntry.updateddate ? moment(directBookingEntry.updateddate) : undefined;
      });
    }
    return res;
  }
}
