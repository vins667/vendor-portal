import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

type EntityResponseType = HttpResponse<ITravelApplicationMaster>;
type EntityArrayResponseType = HttpResponse<ITravelApplicationMaster[]>;

@Injectable({ providedIn: 'root' })
export class TravelApplicationMasterHodService {
  public resourceUrl = SERVER_API_URL + 'api/travel-application-masters';
  public resourceUrlHotel = SERVER_API_URL + 'api/travel-accommodation-details-hod';
  public resourceUrlFx = SERVER_API_URL + 'api/travel-forex-details-hod';
  public resourceUrlFlight = SERVER_API_URL + 'api/travel-flight-details-hod';

  constructor(protected http: HttpClient) {}

  create(travelApplicationMasterHod: ITravelApplicationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelApplicationMasterHod);
    return this.http
      .post<ITravelApplicationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelApplicationMasterHod: ITravelApplicationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelApplicationMasterHod);
    return this.http
      .put<ITravelApplicationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelApplicationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  approve(id: number, flag: string): Observable<EntityResponseType> {
    return this.http
      .get<ITravelApplicationMaster>(`${this.resourceUrl + '-hod-approve'}/${id}/${flag}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: ConveyanceSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<ITravelApplicationMaster[]>(this.resourceUrl + '-hod', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetailRow(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlFlight}/${id}`, { observe: 'response' });
  }

  deleteHotelDtlRow(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlHotel}/${id}`, { observe: 'response' });
  }

  deleteDetailRowFx(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlFx}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelApplicationMasterHod: ITravelApplicationMaster): ITravelApplicationMaster {
    const copy: ITravelApplicationMaster = Object.assign({}, travelApplicationMasterHod, {
      createdDate:
        travelApplicationMasterHod.createdDate != null && travelApplicationMasterHod.createdDate.isValid()
          ? travelApplicationMasterHod.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        travelApplicationMasterHod.lastUpdatedDate != null && travelApplicationMasterHod.lastUpdatedDate.isValid()
          ? travelApplicationMasterHod.lastUpdatedDate.toJSON()
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
      res.body.forEach((travelApplicationMasterHod: ITravelApplicationMaster) => {
        travelApplicationMasterHod.createdDate =
          travelApplicationMasterHod.createdDate != null ? moment(travelApplicationMasterHod.createdDate) : null;
        travelApplicationMasterHod.lastUpdatedDate =
          travelApplicationMasterHod.lastUpdatedDate != null ? moment(travelApplicationMasterHod.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
