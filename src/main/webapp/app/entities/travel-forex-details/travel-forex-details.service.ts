import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITravelForexDetails } from 'app/shared/model/travel-forex-details.model';

type EntityResponseType = HttpResponse<ITravelForexDetails>;
type EntityArrayResponseType = HttpResponse<ITravelForexDetails[]>;

@Injectable({ providedIn: 'root' })
export class TravelForexDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/travel-forex-details';

  constructor(protected http: HttpClient) {}

  create(travelForexDetails: ITravelForexDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelForexDetails);
    return this.http
      .post<ITravelForexDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelForexDetails: ITravelForexDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelForexDetails);
    return this.http
      .put<ITravelForexDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelForexDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelForexDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelForexDetails: ITravelForexDetails): ITravelForexDetails {
    const copy: ITravelForexDetails = Object.assign({}, travelForexDetails, {
      createdDate:
        travelForexDetails.createdDate != null && travelForexDetails.createdDate.isValid() ? travelForexDetails.createdDate.toJSON() : null,
      lastUpdatedDate:
        travelForexDetails.lastUpdatedDate != null && travelForexDetails.lastUpdatedDate.isValid()
          ? travelForexDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((travelForexDetails: ITravelForexDetails) => {
        travelForexDetails.createdDate = travelForexDetails.createdDate != null ? moment(travelForexDetails.createdDate) : null;
        travelForexDetails.lastUpdatedDate = travelForexDetails.lastUpdatedDate != null ? moment(travelForexDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
