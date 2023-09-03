import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';

type EntityResponseType = HttpResponse<IVendTypeMaster>;
type EntityArrayResponseType = HttpResponse<IVendTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class VendTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vend-type-masters';

  constructor(protected http: HttpClient) {}

  create(vendTypeMaster: IVendTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendTypeMaster);
    return this.http
      .post<IVendTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendTypeMaster: IVendTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendTypeMaster);
    return this.http
      .put<IVendTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVendTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vendTypeMaster: IVendTypeMaster): IVendTypeMaster {
    const copy: IVendTypeMaster = Object.assign({}, vendTypeMaster, {
      createdDate: vendTypeMaster.createdDate != null && vendTypeMaster.createdDate.isValid() ? vendTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        vendTypeMaster.lastUpdatedDate != null && vendTypeMaster.lastUpdatedDate.isValid() ? vendTypeMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((vendTypeMaster: IVendTypeMaster) => {
        vendTypeMaster.createdDate = vendTypeMaster.createdDate != null ? moment(vendTypeMaster.createdDate) : null;
        vendTypeMaster.lastUpdatedDate = vendTypeMaster.lastUpdatedDate != null ? moment(vendTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
