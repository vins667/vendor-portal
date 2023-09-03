import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { IDeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<IDeliveryTermMaster>;
type EntityArrayResponseType = HttpResponse<IDeliveryTermMaster[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryTermMasterService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-term-masters';

  constructor(protected http: HttpClient) {}

  create(deliveryTermMaster: IDeliveryTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryTermMaster);
    return this.http
      .post<IDeliveryTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deliveryTermMaster: IDeliveryTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryTermMaster);
    return this.http
      .put<IDeliveryTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryTermMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeliveryTermMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(deliveryTermMaster: IDeliveryTermMaster): IDeliveryTermMaster {
    const copy: IDeliveryTermMaster = Object.assign({}, deliveryTermMaster, {
      createdDate:
        deliveryTermMaster.createdDate != null && deliveryTermMaster.createdDate.isValid() ? deliveryTermMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        deliveryTermMaster.lastUpdatedDate != null && deliveryTermMaster.lastUpdatedDate.isValid()
          ? deliveryTermMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((deliveryTermMaster: IDeliveryTermMaster) => {
        deliveryTermMaster.createdDate = deliveryTermMaster.createdDate != null ? moment(deliveryTermMaster.createdDate) : null;
        deliveryTermMaster.lastUpdatedDate = deliveryTermMaster.lastUpdatedDate != null ? moment(deliveryTermMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
