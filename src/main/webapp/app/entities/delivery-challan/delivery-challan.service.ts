import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeliveryChallanSearch } from 'app/shared/model/delivery-challan-search.model';
import { IFactory } from 'app/shared/model/factory.model';
import { IOrderPartner } from 'app/shared/model/order-partner.model';
import { IDeliveryChallanDetails } from 'app/shared/model/delivery-challan-details.model';

type EntityResponseType = HttpResponse<IDeliveryChallan>;
type EntityResponseTypeTax = HttpResponse<IDeliveryChallanDetails>;
type EntityArrayResponseType = HttpResponse<IDeliveryChallan[]>;
type EntityArrayResponseType1 = HttpResponse<IFactory[]>;
type EntityArrayResponseType2 = HttpResponse<IOrderPartner[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryChallanService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-challans';

  constructor(protected http: HttpClient) {}

  create(deliveryChallan: IDeliveryChallan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryChallan);
    return this.http
      .post<IDeliveryChallan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deliveryChallan: IDeliveryChallan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryChallan);
    return this.http
      .put<IDeliveryChallan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryChallan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByFactory(code: string): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryChallan>(`${this.resourceUrl + '-factory'}/${code}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByPartner(absuniqueid: string): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryChallan>(`${this.resourceUrl + '-partner'}/${absuniqueid}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByTaxPers(tarrifcode: string, isState: string): Observable<EntityResponseTypeTax> {
    return this.http
      .get<IDeliveryChallanDetails>(`${this.resourceUrl + '-tax'}/${tarrifcode}/${isState}`, { observe: 'response' })
      .pipe(map((res: EntityResponseTypeTax) => this.convertDateFromServerTax(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeliveryChallan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(deliveryChallanSearch?: IDeliveryChallanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IDeliveryChallan[]>(this.resourceUrl + '-qry', deliveryChallanSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFactory(deliveryChallanSearch?: IDeliveryChallanSearch): Observable<EntityArrayResponseType1> {
    return this.http
      .post<IFactory[]>(this.resourceUrl + '-factory', deliveryChallanSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType1) => this.convertDateArrayFromServer1(res)));
  }

  queryTariff(deliveryChallanSearch?: IDeliveryChallanSearch): Observable<EntityArrayResponseType1> {
    return this.http
      .post<IFactory[]>(this.resourceUrl + '-tariff', deliveryChallanSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType1) => this.convertDateArrayFromServer1(res)));
  }

  queryPartner(deliveryChallanSearch?: IDeliveryChallanSearch): Observable<EntityArrayResponseType2> {
    return this.http
      .post<IOrderPartner[]>(this.resourceUrl + '-partner', deliveryChallanSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType2) => this.convertDateArrayFromServer2(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl + '-delete'}/${id}`, { observe: 'response' });
  }

  downloadPdf(id: number): Observable<Blob> {
    return this.http.get(`${this.resourceUrl + '-report'}/${id}`, { responseType: 'blob' });
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

  protected convertDateFromServerTax(res: EntityResponseTypeTax): EntityResponseTypeTax {
    return res;
  }

  protected convertDateArrayFromServer1(res: EntityArrayResponseType1): EntityArrayResponseType1 {
    return res;
  }
  protected convertDateArrayFromServer2(res: EntityArrayResponseType2): EntityArrayResponseType2 {
    return res;
  }
}
