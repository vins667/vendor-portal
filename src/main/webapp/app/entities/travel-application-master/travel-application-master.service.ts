import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { ITravelMasterAttach } from 'app/shared/model/travel-master-attach.model';

type EntityResponseType = HttpResponse<ITravelApplicationMaster>;
type EntityArrayResponseType = HttpResponse<ITravelApplicationMaster[]>;

@Injectable({ providedIn: 'root' })
export class TravelApplicationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/travel-application-masters';
  public resourceUrlHotel = SERVER_API_URL + 'api/travel-accommodation-details';
  public resourceUrlFx = SERVER_API_URL + 'api/travel-forex-details';
  public resourceUrlFlight = SERVER_API_URL + 'api/travel-flight-details';
  public resourceUrlPassg = SERVER_API_URL + 'api/travel-passenger-details';
  public resourceUrlFileUpload = SERVER_API_URL + 'api/travel-file-upload-masters';
  public resourceUrlAttach = SERVER_API_URL + 'api/travel-master-attaches';
  constructor(protected http: HttpClient) {}

  create(travelApplicationMaster: ITravelApplicationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelApplicationMaster);
    return this.http
      .post<ITravelApplicationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelApplicationMaster: ITravelApplicationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelApplicationMaster);
    return this.http
      .put<ITravelApplicationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelApplicationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelApplicationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
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

  deleteDetailRowPassg(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlPassg}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlAttach + '-download'}/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  findAttach(id: number): Observable<HttpResponse<ITravelMasterAttach[]>> {
    return this.http.get<ITravelMasterAttach[]>(`${this.resourceUrlAttach}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(travelApplicationMaster: ITravelApplicationMaster): ITravelApplicationMaster {
    const copy: ITravelApplicationMaster = Object.assign({}, travelApplicationMaster, {
      createdDate:
        travelApplicationMaster.createdDate != null && travelApplicationMaster.createdDate.isValid()
          ? travelApplicationMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        travelApplicationMaster.lastUpdatedDate != null && travelApplicationMaster.lastUpdatedDate.isValid()
          ? travelApplicationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((travelApplicationMaster: ITravelApplicationMaster) => {
        travelApplicationMaster.createdDate =
          travelApplicationMaster.createdDate != null ? moment(travelApplicationMaster.createdDate) : null;
        travelApplicationMaster.lastUpdatedDate =
          travelApplicationMaster.lastUpdatedDate != null ? moment(travelApplicationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
