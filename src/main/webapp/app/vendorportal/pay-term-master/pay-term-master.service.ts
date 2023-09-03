import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPayTermMaster } from 'app/shared/model/pay-term-master.model';

type EntityResponseType = HttpResponse<IPayTermMaster>;
type EntityArrayResponseType = HttpResponse<IPayTermMaster[]>;

@Injectable({ providedIn: 'root' })
export class PayTermMasterService {
  public resourceUrl = SERVER_API_URL + 'api/pay-term-masters';

  constructor(protected http: HttpClient) {}

  create(payTermMaster: IPayTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(payTermMaster);
    return this.http
      .post<IPayTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(payTermMaster: IPayTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(payTermMaster);
    return this.http
      .put<IPayTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPayTermMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPayTermMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(payTermMaster: IPayTermMaster): IPayTermMaster {
    const copy: IPayTermMaster = Object.assign({}, payTermMaster, {
      createdDate: payTermMaster.createdDate != null && payTermMaster.createdDate.isValid() ? payTermMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        payTermMaster.lastUpdatedDate != null && payTermMaster.lastUpdatedDate.isValid() ? payTermMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((payTermMaster: IPayTermMaster) => {
        payTermMaster.createdDate = payTermMaster.createdDate != null ? moment(payTermMaster.createdDate) : null;
        payTermMaster.lastUpdatedDate = payTermMaster.lastUpdatedDate != null ? moment(payTermMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
