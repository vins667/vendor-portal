import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEvent } from 'app/shared/model/event.model';
import { IEventAccess } from 'app/shared/model/event-access.model';
import { EventSearch } from 'app/shared/model/event-search.model';

type EntityResponseType = HttpResponse<IEvent>;
type EntityArrayResponseType = HttpResponse<IEvent[]>;

@Injectable({ providedIn: 'root' })
export class EventService {
  public resourceUrl = SERVER_API_URL + 'api/events';
  public resourceUrlAccess = SERVER_API_URL + 'api/events-access';

  constructor(protected http: HttpClient) {}

  create(event: IEvent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(event);
    return this.http
      .post<IEvent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(event: IEvent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(event);
    return this.http
      .put<IEvent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEvent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  eventMonth(date: Date): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEvent[]>(this.resourceUrl + '-month', date, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  eventMonthAndCardNo(date: Date, cardNo: string): Observable<EntityArrayResponseType> {
    const eventSearch = new EventSearch();
    eventSearch.date = date;
    eventSearch.cardNo = cardNo;
    return this.http
      .post<IEvent[]>(this.resourceUrl + '-month-card', eventSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryAccess(): Observable<HttpResponse<IEventAccess[]>> {
    return this.http.get<IEventAccess[]>(this.resourceUrlAccess, { observe: 'response' });
  }

  protected convertDateFromClient(event: IEvent): IEvent {
    const copy: IEvent = Object.assign({}, event, {
      eventFrom: event.eventFrom != null && event.eventFrom.isValid() ? event.eventFrom.toJSON() : null,
      eventTo: event.eventTo != null && event.eventTo.isValid() ? event.eventTo.toJSON() : null,
      createdDate: event.createdDate != null && event.createdDate.isValid() ? event.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.eventFrom = res.body.eventFrom != null ? moment(res.body.eventFrom) : null;
      res.body.eventTo = res.body.eventTo != null ? moment(res.body.eventTo) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((event: IEvent) => {
        event.eventFrom = event.eventFrom != null ? moment(event.eventFrom) : null;
        event.eventTo = event.eventTo != null ? moment(event.eventTo) : null;
        event.createdDate = event.createdDate != null ? moment(event.createdDate) : null;
      });
    }
    return res;
  }
}
