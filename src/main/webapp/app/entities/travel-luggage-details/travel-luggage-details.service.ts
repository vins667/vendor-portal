import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';

type EntityResponseType = HttpResponse<ITravelLuggageDetails>;
type EntityArrayResponseType = HttpResponse<ITravelLuggageDetails[]>;

@Injectable({ providedIn: 'root' })
export class TravelLuggageDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/travel-luggage-details';

  constructor(protected http: HttpClient) {}

  create(travelLuggageDetails: ITravelLuggageDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelLuggageDetails);
    return this.http
      .post<ITravelLuggageDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelLuggageDetails: ITravelLuggageDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelLuggageDetails);
    return this.http
      .put<ITravelLuggageDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelLuggageDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelLuggageDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelLuggageDetails: ITravelLuggageDetails): ITravelLuggageDetails {
    const copy: ITravelLuggageDetails = Object.assign({}, travelLuggageDetails, {
      createdDate:
        travelLuggageDetails.createdDate != null && travelLuggageDetails.createdDate.isValid()
          ? travelLuggageDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        travelLuggageDetails.lastUpdatedDate != null && travelLuggageDetails.lastUpdatedDate.isValid()
          ? travelLuggageDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((travelLuggageDetails: ITravelLuggageDetails) => {
        travelLuggageDetails.createdDate = travelLuggageDetails.createdDate != null ? moment(travelLuggageDetails.createdDate) : null;
        travelLuggageDetails.lastUpdatedDate =
          travelLuggageDetails.lastUpdatedDate != null ? moment(travelLuggageDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
