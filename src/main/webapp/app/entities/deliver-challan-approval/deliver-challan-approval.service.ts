import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IDeliveryChallanSearch } from 'app/shared/model/delivery-challan-search.model';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

type EntityResponseType = HttpResponse<IDeliveryChallan>;
type EntityArrayResponseType = HttpResponse<IDeliveryChallan[]>;

@Injectable({ providedIn: 'root' })
export class DeliverChallanApprovalService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-challans';

  constructor(protected http: HttpClient) {}
  update(deliveryChallan: IDeliveryChallan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryChallan);
    return this.http
      .put<IDeliveryChallan>(this.resourceUrl + '-aprv', copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryChallan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  queryCustom(deliveryChallanSearch?: IDeliveryChallanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IDeliveryChallan[]>(this.resourceUrl + '-aprvqry', deliveryChallanSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
      res.body.approvedDate = res.body.approvedDate != null ? moment(res.body.approvedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((deliveryChallan: IDeliveryChallan) => {
        deliveryChallan.createdDate = deliveryChallan.createdDate != null ? moment(deliveryChallan.createdDate) : null;
        deliveryChallan.lastUpdatedDate = deliveryChallan.lastUpdatedDate != null ? moment(deliveryChallan.lastUpdatedDate) : null;
        deliveryChallan.approvedDate = deliveryChallan.approvedDate != null ? moment(deliveryChallan.approvedDate) : null;
      });
    }
    return res;
  }

  protected convertDateFromClient(deliveryChallan: IDeliveryChallan): IDeliveryChallan {
    const copy: IDeliveryChallan = Object.assign({}, deliveryChallan, {
      createdDate:
        deliveryChallan.createdDate != null && deliveryChallan.createdDate.isValid() ? deliveryChallan.createdDate.toJSON() : null,
      lastUpdatedDate:
        deliveryChallan.lastUpdatedDate != null && deliveryChallan.lastUpdatedDate.isValid()
          ? deliveryChallan.lastUpdatedDate.toJSON()
          : null,
      approvedDate:
        deliveryChallan.approvedDate != null && deliveryChallan.approvedDate.isValid() ? deliveryChallan.approvedDate.toJSON() : null
    });
    return copy;
  }
}
