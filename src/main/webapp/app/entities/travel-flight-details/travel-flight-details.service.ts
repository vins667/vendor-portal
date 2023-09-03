import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITravelFlightDetails } from 'app/shared/model/travel-flight-details.model';

type EntityResponseType = HttpResponse<ITravelFlightDetails>;
type EntityArrayResponseType = HttpResponse<ITravelFlightDetails[]>;

@Injectable({ providedIn: 'root' })
export class TravelFlightDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/travel-flight-details';

  constructor(protected http: HttpClient) {}

  create(travelFlightDetails: ITravelFlightDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelFlightDetails);
    return this.http
      .post<ITravelFlightDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelFlightDetails: ITravelFlightDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelFlightDetails);
    return this.http
      .put<ITravelFlightDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelFlightDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelFlightDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelFlightDetails: ITravelFlightDetails): ITravelFlightDetails {
    const copy: ITravelFlightDetails = Object.assign({}, travelFlightDetails, {
      travelDate:
        travelFlightDetails.travelDate != null && travelFlightDetails.travelDate.isValid() ? travelFlightDetails.travelDate.toJSON() : null,
      departureDate:
        travelFlightDetails.departureDate != null && travelFlightDetails.departureDate.isValid()
          ? travelFlightDetails.departureDate.toJSON()
          : null,
      arrivalDate:
        travelFlightDetails.arrivalDate != null && travelFlightDetails.arrivalDate.isValid()
          ? travelFlightDetails.arrivalDate.toJSON()
          : null,
      createdDate:
        travelFlightDetails.createdDate != null && travelFlightDetails.createdDate.isValid()
          ? travelFlightDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        travelFlightDetails.lastUpdatedDate != null && travelFlightDetails.lastUpdatedDate.isValid()
          ? travelFlightDetails.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.travelDate = res.body.travelDate != null ? moment(res.body.travelDate) : null;
      res.body.departureDate = res.body.departureDate != null ? moment(res.body.departureDate) : null;
      res.body.arrivalDate = res.body.arrivalDate != null ? moment(res.body.arrivalDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((travelFlightDetails: ITravelFlightDetails) => {
        travelFlightDetails.travelDate = travelFlightDetails.travelDate != null ? moment(travelFlightDetails.travelDate) : null;
        travelFlightDetails.departureDate = travelFlightDetails.departureDate != null ? moment(travelFlightDetails.departureDate) : null;
        travelFlightDetails.arrivalDate = travelFlightDetails.arrivalDate != null ? moment(travelFlightDetails.arrivalDate) : null;
        travelFlightDetails.createdDate = travelFlightDetails.createdDate != null ? moment(travelFlightDetails.createdDate) : null;
        travelFlightDetails.lastUpdatedDate =
          travelFlightDetails.lastUpdatedDate != null ? moment(travelFlightDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
