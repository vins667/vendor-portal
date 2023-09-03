import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPollDetails } from 'app/shared/model/poll-details.model';

type EntityResponseType = HttpResponse<IPollDetails>;
type EntityArrayResponseType = HttpResponse<IPollDetails[]>;

@Injectable({ providedIn: 'root' })
export class PollDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/poll-details';

  constructor(protected http: HttpClient) {}

  create(pollDetails: IPollDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pollDetails);
    return this.http
      .post<IPollDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(pollDetails: IPollDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pollDetails);
    return this.http
      .put<IPollDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPollDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPollDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(pollDetails: IPollDetails): IPollDetails {
    const copy: IPollDetails = Object.assign({}, pollDetails, {
      createdDate: pollDetails.createdDate != null && pollDetails.createdDate.isValid() ? pollDetails.createdDate.toJSON() : null
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
      res.body.forEach((pollDetails: IPollDetails) => {
        pollDetails.createdDate = pollDetails.createdDate != null ? moment(pollDetails.createdDate) : null;
      });
    }
    return res;
  }
}
