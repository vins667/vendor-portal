import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { IMarkerEntryDetails } from 'app/shared/model/marker-entry-details.model';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';
import { IDirectBookingSearch } from 'app/shared/model/direct-booking-search.model';
import { ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { IBalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';
import { IMarkerDestinationBean } from 'app/shared/model/marker-destination-bean.model';

type EntityResponseType = HttpResponse<IMarkerMasterEntry>;
type EntityArrayResponseType = HttpResponse<IMarkerMasterEntry[]>;

@Injectable({ providedIn: 'root' })
export class MarkerMasterEntryService {
  public markerMasterEntry?: IMarkerMasterEntry;
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';
  public resourceUrl = SERVER_API_URL + 'api/marker-master-entries';
  public resourceUrlView = SERVER_API_URL + 'api/marker-master-entries-fetch';
  public resourceUrlViewApp = SERVER_API_URL + 'api/marker-master-entries-fetch-approved';
  public resourceUrlViewAvg = SERVER_API_URL + 'api/marker-master-entries-fetch-avg';
  public resourceUrlUserPlant = SERVER_API_URL + 'api/user-plants';
  public resourceUrlCutting = SERVER_API_URL + 'api/marker-master-entries-cutting';
  public resourceUrlFilter = SERVER_API_URL + 'api/marker-master-entries-filter';
  public resourceUrlList = SERVER_API_URL + 'api/marker-master-entities-list';
  public resourceUrlFetch = SERVER_API_URL + 'api/fetch-marker-master-entities';
  public resourceUrlDb2 = SERVER_API_URL + 'api/db2-salesorder-countries';
  public resourceUrldb2factoryByStyle = SERVER_API_URL + 'api/db2-salesorder-style-factory';
  public resourceUrldb2Color = SERVER_API_URL + 'api/db2-salesorder-colors';
  // public resourceUrldb2ColorByFactory = SERVER_API_URL + 'api/db2-salesorder-colors-factory';
  public resourceUrldb2Size = SERVER_API_URL + 'api/db2-salesorder-size';
  public resourceUrldb2reservationItem = SERVER_API_URL + 'api/db2-prodres-marker-itemcode';
  public resourceUrldb2reservationItemdestinations = SERVER_API_URL + 'api/db2-viewsalesorder-destinations';

  constructor(protected http: HttpClient) {}

  create(markerMasterEntries: IMarkerMasterEntry[]): Observable<EntityArrayResponseType> {
    const copy = this.convertDateArrayFromClient(markerMasterEntries);
    return this.http
      .post<IMarkerMasterEntry[]>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  update(markerMasterEntry: IMarkerMasterEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(markerMasterEntry);
    return this.http
      .put<IMarkerMasterEntry>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  plants(): Observable<HttpResponse<IUserPlant[]>> {
    return this.http.get<IUserPlant[]>(this.resourceUrlUserPlant, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMarkerMasterEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  forward(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMarkerMasterEntry>(`${this.resourceUrl + '-forward'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  cutting(search: ICutPlanSearch): Observable<EntityResponseType> {
    return this.http
      .post<IMarkerMasterEntry>(`${this.resourceUrlCutting}`, search, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMarkerMasterEntry[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IDirectBookingSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IMarkerMasterEntry[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  view(markerMasterEntry: IMarkerMasterEntry): Observable<EntityArrayResponseType> {
    return this.http
      .post<IMarkerMasterEntry[]>(this.resourceUrlView, markerMasterEntry, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  viewApp(markerMasterEntry: IMarkerMasterEntry): Observable<EntityArrayResponseType> {
    return this.http
      .post<IMarkerMasterEntry[]>(this.resourceUrlViewApp, markerMasterEntry, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  viewAvg(markerMasterEntry: IMarkerMasterEntry): Observable<EntityResponseType> {
    return this.http
      .post<IMarkerMasterEntry>(this.resourceUrlViewAvg, markerMasterEntry, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  fetch(master: IMaster): Observable<EntityResponseType> {
    return this.http
      .post<IMarkerMasterEntry>(this.resourceUrlList, master, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  fetchMarkers(master: IBalanceSuggestionSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IMarkerMasterEntry[]>(this.resourceUrlFetch, master, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCountryById(styleCode: string): Observable<HttpResponse<IMaster[]>> {
    const master = new Master();
    master.name = styleCode;
    return this.http.post<IMaster[]>(this.resourceUrlDb2, master, { observe: 'response' });
  }

  queryStyleById(master: Master): Observable<HttpResponse<IMaster>> {
    return this.http.post<IMaster>(this.resourceUrldb2factoryByStyle, master, { observe: 'response' });
  }

  queryColorByCountry(master: Master): Observable<HttpResponse<IMaster[]>> {
    return this.http.post<IMaster[]>(this.resourceUrldb2Color, master, { observe: 'response' });
  }

  querySizeByStyle(master: Master): Observable<HttpResponse<IMarkerDestinationBean>> {
    return this.http.post<IMarkerDestinationBean>(this.resourceUrldb2Size, master, { observe: 'response' });
  }

  querySizeByStyleDestination(master: IBalanceSuggestionSearch): Observable<HttpResponse<IMarkerDestinationBean>> {
    return this.http.post<IMarkerDestinationBean>(this.resourceUrldb2reservationItemdestinations, master, { observe: 'response' });
  }

  getAllReservationItemByPo(master: Master): Observable<HttpResponse<IFullitemkeydecoder[]>> {
    return this.http.post<IFullitemkeydecoder[]>(this.resourceUrldb2reservationItem, master, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(markerMasterEntry: IMarkerMasterEntry): IMarkerMasterEntry {
    const copy: IMarkerMasterEntry = Object.assign({}, markerMasterEntry, {
      createdDate:
        markerMasterEntry.createdDate != null && markerMasterEntry.createdDate.isValid() ? markerMasterEntry.createdDate.toJSON() : null,
      lastUpdatedDate:
        markerMasterEntry.lastUpdatedDate != null && markerMasterEntry.lastUpdatedDate.isValid()
          ? markerMasterEntry.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateArrayFromClient(markerMasterEntries: IMarkerMasterEntry[]): IMarkerMasterEntry[] {
    if (markerMasterEntries) {
      markerMasterEntries.forEach((markerMasterEntry: IMarkerMasterEntry) => {
        markerMasterEntry.createdDate =
          markerMasterEntry.createdDate != null && markerMasterEntry.createdDate.isValid() ? markerMasterEntry.createdDate.toJSON() : null;
        markerMasterEntry.lastUpdatedDate =
          markerMasterEntry.lastUpdatedDate != null && markerMasterEntry.lastUpdatedDate.isValid()
            ? markerMasterEntry.lastUpdatedDate.toJSON()
            : null;
      });
    }
    return markerMasterEntries;
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
      res.body.forEach((markerMasterEntry: IMarkerMasterEntry) => {
        markerMasterEntry.createdDate = markerMasterEntry.createdDate != null ? moment(markerMasterEntry.createdDate) : null;
        markerMasterEntry.lastUpdatedDate = markerMasterEntry.lastUpdatedDate != null ? moment(markerMasterEntry.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
