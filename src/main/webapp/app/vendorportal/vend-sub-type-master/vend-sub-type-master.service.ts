import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';

type EntityResponseType = HttpResponse<IVendSubTypeMaster>;
type EntityArrayResponseType = HttpResponse<IVendSubTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class VendSubTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vend-sub-type-masters';

  constructor(protected http: HttpClient) {}

  create(vendSubTypeMaster: IVendSubTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendSubTypeMaster);
    return this.http
      .post<IVendSubTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendSubTypeMaster: IVendSubTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendSubTypeMaster);
    return this.http
      .put<IVendSubTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendSubTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByVendType(id: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IVendSubTypeMaster[]>(`${this.resourceUrl + '-vend-type'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVendSubTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vendSubTypeMaster: IVendSubTypeMaster): IVendSubTypeMaster {
    const copy: IVendSubTypeMaster = Object.assign({}, vendSubTypeMaster, {
      createdDate:
        vendSubTypeMaster.createdDate != null && vendSubTypeMaster.createdDate.isValid() ? vendSubTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        vendSubTypeMaster.lastUpdatedDate != null && vendSubTypeMaster.lastUpdatedDate.isValid()
          ? vendSubTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((vendSubTypeMaster: IVendSubTypeMaster) => {
        vendSubTypeMaster.createdDate = vendSubTypeMaster.createdDate != null ? moment(vendSubTypeMaster.createdDate) : null;
        vendSubTypeMaster.lastUpdatedDate = vendSubTypeMaster.lastUpdatedDate != null ? moment(vendSubTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
