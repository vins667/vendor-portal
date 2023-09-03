import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { IMarkerMasterSearch } from 'app/shared/model/marker-master-search.model';

type EntityResponseType = HttpResponse<IMarkerMasterEntry>;
type EntityArrayResponseType = HttpResponse<IMarkerMasterEntry[]>;

@Injectable({ providedIn: 'root' })
export class MarkerEntryApprovalService {
  public markerMasterEntry?: IMarkerMasterEntry;
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';
  public resourceUrl = SERVER_API_URL + 'api/marker-master-entries';
  public resourceUrlFilter = SERVER_API_URL + 'api/marker-entry-approval-filter';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMarkerMasterEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMarkerMasterEntry[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: IMarkerMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IMarkerMasterEntry[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  forwardApproval(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMarkerMasterEntry>(`${this.resourceUrl + '-approval'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  forwardRejection(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMarkerMasterEntry>(`${this.resourceUrl + '-reject'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  forwardReturn(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMarkerMasterEntry>(`${this.resourceUrl + '-return'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
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
