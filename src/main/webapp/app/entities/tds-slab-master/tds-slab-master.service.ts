import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITdsSlabMaster } from 'app/shared/model/tds-slab-master.model';
import { TdsSlabSearchMaster } from 'app/shared/model/tds-slab-search-master.model';

type EntityResponseType = HttpResponse<ITdsSlabMaster>;
type EntityArrayResponseType = HttpResponse<ITdsSlabMaster[]>;

@Injectable({ providedIn: 'root' })
export class TdsSlabMasterService {
  public resourceUrl = SERVER_API_URL + 'api/tds-slab-masters';

  constructor(protected http: HttpClient) {}

  create(tdsSlabMaster: ITdsSlabMaster[]): Observable<EntityResponseType> {
    return this.http
      .post<ITdsSlabMaster>(this.resourceUrl, tdsSlabMaster, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  edit(req?: TdsSlabSearchMaster): Observable<EntityResponseType> {
    return this.http.post<TdsSlabSearchMaster>(this.resourceUrl + '-edit', req, { observe: 'response' });
  }
  update(tdsSlabMaster: ITdsSlabMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tdsSlabMaster);
    return this.http
      .put<ITdsSlabMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITdsSlabMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITdsSlabMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tdsSlabMaster: ITdsSlabMaster): ITdsSlabMaster {
    const copy: ITdsSlabMaster = Object.assign({}, tdsSlabMaster, {
      createdDate: tdsSlabMaster.createdDate != null && tdsSlabMaster.createdDate.isValid() ? tdsSlabMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tdsSlabMaster: ITdsSlabMaster) => {
        tdsSlabMaster.createdDate = tdsSlabMaster.createdDate != null ? moment(tdsSlabMaster.createdDate) : null;
      });
    }
    return res;
  }
}
