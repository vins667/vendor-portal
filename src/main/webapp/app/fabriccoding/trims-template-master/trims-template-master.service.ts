import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';

type EntityResponseType = HttpResponse<ITrimsTemplateMaster>;
type EntityArrayResponseType = HttpResponse<ITrimsTemplateMaster[]>;

@Injectable({ providedIn: 'root' })
export class TrimsTemplateMasterService {
  public resourceUrl = SERVER_API_URL + 'api/trims-template-masters';
  public resourceUrlDetails = SERVER_API_URL + 'api/trim-template-details';
  public resourceUrlBreakup = SERVER_API_URL + 'api/trims-template-details-breakups';

  constructor(protected http: HttpClient) {}

  create(trimsTemplateMaster: ITrimsTemplateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trimsTemplateMaster);
    return this.http
      .post<ITrimsTemplateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(trimsTemplateMaster: ITrimsTemplateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trimsTemplateMaster);
    return this.http
      .put<ITrimsTemplateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITrimsTemplateMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITrimsTemplateMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  deleteBreakUp(id: number, did: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlBreakup}/${id}/${did}`, { observe: 'response' });
  }

  protected convertDateFromClient(trimsTemplateMaster: ITrimsTemplateMaster): ITrimsTemplateMaster {
    const copy: ITrimsTemplateMaster = Object.assign({}, trimsTemplateMaster, {
      createdDate:
        trimsTemplateMaster.createdDate != null && trimsTemplateMaster.createdDate.isValid()
          ? trimsTemplateMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        trimsTemplateMaster.lastUpdatedDate != null && trimsTemplateMaster.lastUpdatedDate.isValid()
          ? trimsTemplateMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((trimsTemplateMaster: ITrimsTemplateMaster) => {
        trimsTemplateMaster.createdDate = trimsTemplateMaster.createdDate != null ? moment(trimsTemplateMaster.createdDate) : null;
        trimsTemplateMaster.lastUpdatedDate =
          trimsTemplateMaster.lastUpdatedDate != null ? moment(trimsTemplateMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
