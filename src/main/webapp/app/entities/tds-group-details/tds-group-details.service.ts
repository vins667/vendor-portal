import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITdsGroupDetails } from 'app/shared/model/tds-group-details.model';

type EntityResponseType = HttpResponse<ITdsGroupDetails>;
type EntityArrayResponseType = HttpResponse<ITdsGroupDetails[]>;

@Injectable({ providedIn: 'root' })
export class TdsGroupDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/tds-group-details';

  constructor(protected http: HttpClient) {}

  create(tdsGroupDetails: ITdsGroupDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsGroupDetails);
    return this.http
      .post<ITdsGroupDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tdsGroupDetails: ITdsGroupDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsGroupDetails);
    return this.http
      .put<ITdsGroupDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITdsGroupDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITdsGroupDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tdsGroupDetails: ITdsGroupDetails): ITdsGroupDetails {
    const copy: ITdsGroupDetails = Object.assign({}, tdsGroupDetails, {
      lastUpdatedDate:
        tdsGroupDetails.lastUpdatedDate != null && tdsGroupDetails.lastUpdatedDate.isValid()
          ? tdsGroupDetails.lastUpdatedDate.toJSON()
          : null,
      createdDate:
        tdsGroupDetails.createdDate != null && tdsGroupDetails.createdDate.isValid() ? tdsGroupDetails.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tdsGroupDetails: ITdsGroupDetails) => {
        tdsGroupDetails.lastUpdatedDate = tdsGroupDetails.lastUpdatedDate != null ? moment(tdsGroupDetails.lastUpdatedDate) : null;
        tdsGroupDetails.createdDate = tdsGroupDetails.createdDate != null ? moment(tdsGroupDetails.createdDate) : null;
      });
    }
    return res;
  }
}
