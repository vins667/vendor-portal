import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';

type EntityResponseType = HttpResponse<IFabricSubstractDetails>;
type EntityArrayResponseType = HttpResponse<IFabricSubstractDetails[]>;

@Injectable({ providedIn: 'root' })
export class FabricSubstractDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-substract-details';

  constructor(protected http: HttpClient) {}

  create(fabricSubstractDetails: IFabricSubstractDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricSubstractDetails);
    return this.http
      .post<IFabricSubstractDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fabricSubstractDetails: IFabricSubstractDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricSubstractDetails);
    return this.http
      .put<IFabricSubstractDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFabricSubstractDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByMaster(id: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IFabricSubstractDetails[]>(`${this.resourceUrl + '-by-master-id'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFabricSubstractDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fabricSubstractDetails: IFabricSubstractDetails): IFabricSubstractDetails {
    const copy: IFabricSubstractDetails = Object.assign({}, fabricSubstractDetails, {
      createdDate:
        fabricSubstractDetails.createdDate != null && fabricSubstractDetails.createdDate.isValid()
          ? fabricSubstractDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        fabricSubstractDetails.lastUpdatedDate != null && fabricSubstractDetails.lastUpdatedDate.isValid()
          ? fabricSubstractDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((fabricSubstractDetails: IFabricSubstractDetails) => {
        fabricSubstractDetails.createdDate = fabricSubstractDetails.createdDate != null ? moment(fabricSubstractDetails.createdDate) : null;
        fabricSubstractDetails.lastUpdatedDate =
          fabricSubstractDetails.lastUpdatedDate != null ? moment(fabricSubstractDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
