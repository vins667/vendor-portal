import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGstReconciliation } from 'app/shared/model/gst-reconciliation.model';
import { IMessage } from 'app/shared/model/message.model';
import { IParameterList } from 'app/shared/model/parameter-list';

type EntityResponseType = HttpResponse<IGstReconciliation>;
type EntityArrayResponseType = HttpResponse<IGstReconciliation[]>;

@Injectable({ providedIn: 'root' })
export class GstReconciliationService {
  public resourceUrl = SERVER_API_URL + 'api/gst-reconciliations';
  constructor(protected http: HttpClient) {}

  saveReco(gstReconciliations: IGstReconciliation[]): Observable<HttpResponse<IMessage>> {
    const gstReconciliation = [];
    gstReconciliations.forEach(gst => {
      const copy = this.convertDateFromClient(gst);
      gstReconciliation.push(copy);
    });
    return this.http.post<IMessage>(this.resourceUrl, gstReconciliation, { observe: 'response' });
  }

  create(gstReconciliation: IGstReconciliation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gstReconciliation);
    return this.http
      .post<IGstReconciliation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gstReconciliation: IGstReconciliation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gstReconciliation);
    return this.http
      .put<IGstReconciliation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGstReconciliation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGstReconciliation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gstReconciliation: IGstReconciliation): IGstReconciliation {
    const copy: IGstReconciliation = Object.assign({}, gstReconciliation, {
      invoiceDate:
        gstReconciliation.invoiceDate != null && gstReconciliation.invoiceDate.isValid() ? gstReconciliation.invoiceDate.toJSON() : null,
      creationDate:
        gstReconciliation.creationDate != null && gstReconciliation.creationDate.isValid() ? gstReconciliation.creationDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.invoiceDate = res.body.invoiceDate != null ? moment(res.body.invoiceDate) : null;
      res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gstReconciliation: IGstReconciliation) => {
        gstReconciliation.invoiceDate = gstReconciliation.invoiceDate != null ? moment(gstReconciliation.invoiceDate) : null;
        gstReconciliation.creationDate = gstReconciliation.creationDate != null ? moment(gstReconciliation.creationDate) : null;
      });
    }
    return res;
  }

  reconsiles(req?: IParameterList): Observable<EntityArrayResponseType> {
    return this.http
      .post<IGstReconciliation[]>(this.resourceUrl + '-check', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  reconsilesMisc(req?: IParameterList): Observable<EntityArrayResponseType> {
    return this.http
      .post<IGstReconciliation[]>(this.resourceUrl + '-misc', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
}
