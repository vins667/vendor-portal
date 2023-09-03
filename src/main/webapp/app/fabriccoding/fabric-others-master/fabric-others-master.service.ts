import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricOthersMaster } from 'app/shared/model/fabric-others-master.model';

type EntityResponseType = HttpResponse<IFabricOthersMaster>;
type EntityArrayResponseType = HttpResponse<IFabricOthersMaster[]>;

@Injectable({ providedIn: 'root' })
export class FabricOthersMasterService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-others-masters';

  constructor(protected http: HttpClient) {}

  create(fabricOthersMaster: IFabricOthersMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricOthersMaster);
    return this.http
      .post<IFabricOthersMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fabricOthersMaster: IFabricOthersMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricOthersMaster);
    return this.http
      .put<IFabricOthersMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFabricOthersMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFabricOthersMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fabricOthersMaster: IFabricOthersMaster): IFabricOthersMaster {
    const copy: IFabricOthersMaster = Object.assign({}, fabricOthersMaster, {
      createdDate:
        fabricOthersMaster.createdDate != null && fabricOthersMaster.createdDate.isValid() ? fabricOthersMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        fabricOthersMaster.lastUpdatedDate != null && fabricOthersMaster.lastUpdatedDate.isValid()
          ? fabricOthersMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((fabricOthersMaster: IFabricOthersMaster) => {
        fabricOthersMaster.createdDate = fabricOthersMaster.createdDate != null ? moment(fabricOthersMaster.createdDate) : null;
        fabricOthersMaster.lastUpdatedDate = fabricOthersMaster.lastUpdatedDate != null ? moment(fabricOthersMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
