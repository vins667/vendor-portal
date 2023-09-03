import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMMRMaster } from 'app/shared/model/mmr-master.model';
import { IMMRSearchBean } from 'app/shared/model/mmr-search-bean.model';

type EntityResponseType = HttpResponse<IMMRMaster>;
type EntityArrayResponseType = HttpResponse<IMMRMaster[]>;

@Injectable({ providedIn: 'root' })
export class MMRMasterService {
  public resourceUrl = SERVER_API_URL + 'api/mmr-masters';

  constructor(protected http: HttpClient) {}

  create(mMRMaster: IMMRMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mMRMaster);
    return this.http
      .post<IMMRMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(mMRMaster: IMMRMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mMRMaster);
    return this.http
      .put<IMMRMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMMRMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  edit(req?: IMMRSearchBean): Observable<EntityResponseType> {
    return this.http.post<IMMRMaster>(this.resourceUrl + '-edit', req, { observe: 'response' });
  }

  customFind(mmrSerachBean: IMMRSearchBean): Observable<EntityResponseType> {
    return this.http
      .post<IMMRMaster>(`${this.resourceUrl + '-custom'}`, mmrSerachBean, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  copyPrevious(mmrSerachBean: IMMRSearchBean): Observable<EntityResponseType> {
    return this.http
      .post<IMMRMaster>(`${this.resourceUrl + '-custom-copy'}`, mmrSerachBean, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMMRMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(mMRMaster: IMMRMaster): IMMRMaster {
    const copy: IMMRMaster = Object.assign({}, mMRMaster, {
      createdDate: mMRMaster.createdDate != null && mMRMaster.createdDate.isValid() ? mMRMaster.createdDate.toJSON() : null,
      lastUpdatedDate: mMRMaster.lastUpdatedDate != null && mMRMaster.lastUpdatedDate.isValid() ? mMRMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((mMRMaster: IMMRMaster) => {
        mMRMaster.createdDate = mMRMaster.createdDate != null ? moment(mMRMaster.createdDate) : null;
        mMRMaster.lastUpdatedDate = mMRMaster.lastUpdatedDate != null ? moment(mMRMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
