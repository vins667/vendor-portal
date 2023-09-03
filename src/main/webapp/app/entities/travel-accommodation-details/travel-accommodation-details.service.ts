import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';

type EntityResponseType = HttpResponse<ITravelAccommodationDetails>;
type EntityArrayResponseType = HttpResponse<ITravelAccommodationDetails[]>;

@Injectable({ providedIn: 'root' })
export class TravelAccommodationDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/travel-accommodation-details';

  constructor(protected http: HttpClient) {}

  create(travelAccommodationDetails: ITravelAccommodationDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelAccommodationDetails);
    return this.http
      .post<ITravelAccommodationDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelAccommodationDetails: ITravelAccommodationDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelAccommodationDetails);
    return this.http
      .put<ITravelAccommodationDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelAccommodationDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelAccommodationDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelAccommodationDetails: ITravelAccommodationDetails): ITravelAccommodationDetails {
    const copy: ITravelAccommodationDetails = Object.assign({}, travelAccommodationDetails, {
      accommodationDate:
        travelAccommodationDetails.accommodationDate != null && travelAccommodationDetails.accommodationDate.isValid()
          ? travelAccommodationDetails.accommodationDate.toJSON()
          : null,
      createdDate:
        travelAccommodationDetails.createdDate != null && travelAccommodationDetails.createdDate.isValid()
          ? travelAccommodationDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        travelAccommodationDetails.lastUpdatedDate != null && travelAccommodationDetails.lastUpdatedDate.isValid()
          ? travelAccommodationDetails.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.accommodationDate = res.body.accommodationDate != null ? moment(res.body.accommodationDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((travelAccommodationDetails: ITravelAccommodationDetails) => {
        travelAccommodationDetails.accommodationDate =
          travelAccommodationDetails.accommodationDate != null ? moment(travelAccommodationDetails.accommodationDate) : null;
        travelAccommodationDetails.createdDate =
          travelAccommodationDetails.createdDate != null ? moment(travelAccommodationDetails.createdDate) : null;
        travelAccommodationDetails.lastUpdatedDate =
          travelAccommodationDetails.lastUpdatedDate != null ? moment(travelAccommodationDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
