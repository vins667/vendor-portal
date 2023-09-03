import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { IShipmentTermMaster } from 'app/shared/model/shipment-term-master.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<IShipmentTermMaster>;
type EntityArrayResponseType = HttpResponse<IShipmentTermMaster[]>;

@Injectable({ providedIn: 'root' })
export class ShipmentTermMasterService {
  public resourceUrl = SERVER_API_URL + 'api/shipment-term-masters';

  constructor(protected http: HttpClient) {}

  create(shipmentTermMaster: IShipmentTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shipmentTermMaster);
    return this.http
      .post<IShipmentTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(shipmentTermMaster: IShipmentTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shipmentTermMaster);
    return this.http
      .put<IShipmentTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IShipmentTermMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IShipmentTermMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(shipmentTermMaster: IShipmentTermMaster): IShipmentTermMaster {
    const copy: IShipmentTermMaster = Object.assign({}, shipmentTermMaster, {
      createdDate:
        shipmentTermMaster.createdDate != null && shipmentTermMaster.createdDate.isValid() ? shipmentTermMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        shipmentTermMaster.lastUpdatedDate != null && shipmentTermMaster.lastUpdatedDate.isValid()
          ? shipmentTermMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((shipmentTermMaster: IShipmentTermMaster) => {
        shipmentTermMaster.createdDate = shipmentTermMaster.createdDate != null ? moment(shipmentTermMaster.createdDate) : null;
        shipmentTermMaster.lastUpdatedDate = shipmentTermMaster.lastUpdatedDate != null ? moment(shipmentTermMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
