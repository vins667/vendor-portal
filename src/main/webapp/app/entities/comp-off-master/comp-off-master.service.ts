import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompOffMaster } from 'app/shared/model/comp-off-master.model';

type EntityResponseType = HttpResponse<ICompOffMaster>;
type EntityArrayResponseType = HttpResponse<ICompOffMaster[]>;

@Injectable({ providedIn: 'root' })
export class CompOffMasterService {
  public resourceUrl = SERVER_API_URL + 'api/comp-off-masters';

  constructor(protected http: HttpClient) {}

  create(compOffMaster: ICompOffMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(compOffMaster);
    return this.http
      .post<ICompOffMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(compOffMaster: ICompOffMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(compOffMaster);
    return this.http
      .put<ICompOffMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICompOffMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICompOffMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  fetch(req?: any): Observable<EntityResponseType> {
    return this.http
      .post<ICompOffMaster>(this.resourceUrl + '-fetch', req, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(compOffMaster: ICompOffMaster): ICompOffMaster {
    const copy: ICompOffMaster = Object.assign({}, compOffMaster, {
      compOffDate: compOffMaster.compOffDate != null && compOffMaster.compOffDate.isValid() ? compOffMaster.compOffDate.toJSON() : null,
      availDate: compOffMaster.availDate != null && compOffMaster.availDate.isValid() ? compOffMaster.availDate.toJSON() : null,
      hodApprovedDate:
        compOffMaster.hodApprovedDate != null && compOffMaster.hodApprovedDate.isValid() ? compOffMaster.hodApprovedDate.toJSON() : null,
      createdDate: compOffMaster.createdDate != null && compOffMaster.createdDate.isValid() ? compOffMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.compOffDate = res.body.compOffDate != null ? moment(res.body.compOffDate) : null;
      res.body.availDate = res.body.availDate != null ? moment(res.body.availDate) : null;
      res.body.hodApprovedDate = res.body.hodApprovedDate != null ? moment(res.body.hodApprovedDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((compOffMaster: ICompOffMaster) => {
        compOffMaster.compOffDate = compOffMaster.compOffDate != null ? moment(compOffMaster.compOffDate) : null;
        compOffMaster.availDate = compOffMaster.availDate != null ? moment(compOffMaster.availDate) : null;
        compOffMaster.hodApprovedDate = compOffMaster.hodApprovedDate != null ? moment(compOffMaster.hodApprovedDate) : null;
        compOffMaster.createdDate = compOffMaster.createdDate != null ? moment(compOffMaster.createdDate) : null;
      });
    }
    return res;
  }
}
