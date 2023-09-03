import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITaxTermMaster } from 'app/shared/model/tax-term-master.model';

type EntityResponseType = HttpResponse<ITaxTermMaster>;
type EntityArrayResponseType = HttpResponse<ITaxTermMaster[]>;

@Injectable({ providedIn: 'root' })
export class TaxTermMasterService {
  public resourceUrl = SERVER_API_URL + 'api/tax-term-masters';

  constructor(protected http: HttpClient) {}

  create(taxTermMaster: ITaxTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxTermMaster);
    return this.http
      .post<ITaxTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(taxTermMaster: ITaxTermMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxTermMaster);
    return this.http
      .put<ITaxTermMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITaxTermMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITaxTermMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(taxTermMaster: ITaxTermMaster): ITaxTermMaster {
    const copy: ITaxTermMaster = Object.assign({}, taxTermMaster, {
      createdDate: taxTermMaster.createdDate != null && taxTermMaster.createdDate.isValid() ? taxTermMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        taxTermMaster.lastUpdatedDate != null && taxTermMaster.lastUpdatedDate.isValid() ? taxTermMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((taxTermMaster: ITaxTermMaster) => {
        taxTermMaster.createdDate = taxTermMaster.createdDate != null ? moment(taxTermMaster.createdDate) : null;
        taxTermMaster.lastUpdatedDate = taxTermMaster.lastUpdatedDate != null ? moment(taxTermMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
