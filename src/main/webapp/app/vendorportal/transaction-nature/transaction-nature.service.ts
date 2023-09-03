import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransactionNature } from 'app/shared/model/transaction-nature.model';

type EntityResponseType = HttpResponse<ITransactionNature>;
type EntityArrayResponseType = HttpResponse<ITransactionNature[]>;

@Injectable({ providedIn: 'root' })
export class TransactionNatureService {
  public resourceUrl = SERVER_API_URL + 'api/transaction-natures';

  constructor(protected http: HttpClient) {}

  create(transactionNature: ITransactionNature): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(transactionNature);
    return this.http
      .post<ITransactionNature>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(transactionNature: ITransactionNature): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(transactionNature);
    return this.http
      .put<ITransactionNature>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITransactionNature>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITransactionNature[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(transactionNature: ITransactionNature): ITransactionNature {
    const copy: ITransactionNature = Object.assign({}, transactionNature, {
      createdDate:
        transactionNature.createdDate != null && transactionNature.createdDate.isValid() ? transactionNature.createdDate.toJSON() : null,
      lastUpdatedDate:
        transactionNature.lastUpdatedDate != null && transactionNature.lastUpdatedDate.isValid()
          ? transactionNature.lastUpdatedDate.toJSON()
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
      res.body.forEach((transactionNature: ITransactionNature) => {
        transactionNature.createdDate = transactionNature.createdDate != null ? moment(transactionNature.createdDate) : null;
        transactionNature.lastUpdatedDate = transactionNature.lastUpdatedDate != null ? moment(transactionNature.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
