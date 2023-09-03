import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITemplateMaster } from 'app/shared/model/template-master.model';

type EntityResponseType = HttpResponse<ITemplateMaster>;
type EntityArrayResponseType = HttpResponse<ITemplateMaster[]>;

@Injectable({ providedIn: 'root' })
export class TemplateMasterService {
  public resourceUrl = SERVER_API_URL + 'api/template-masters';
  public resourceUrlDetails = SERVER_API_URL + 'api/template-details';

  constructor(protected http: HttpClient) {}

  create(templateMaster: ITemplateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(templateMaster);
    return this.http
      .post<ITemplateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(templateMaster: ITemplateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(templateMaster);
    return this.http
      .put<ITemplateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITemplateMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  copy(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITemplateMaster>(`${this.resourceUrl + '-copy'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITemplateMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  active(templateMaster: ITemplateMaster): Observable<EntityResponseType> {
    return this.http
      .post<ITemplateMaster>(this.resourceUrl + '-active', templateMaster, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  protected convertDateFromClient(templateMaster: ITemplateMaster): ITemplateMaster {
    const copy: ITemplateMaster = Object.assign({}, templateMaster, {
      createdDate: templateMaster.createdDate != null && templateMaster.createdDate.isValid() ? templateMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        templateMaster.lastUpdatedDate != null && templateMaster.lastUpdatedDate.isValid() ? templateMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((templateMaster: ITemplateMaster) => {
        templateMaster.createdDate = templateMaster.createdDate != null ? moment(templateMaster.createdDate) : null;
        templateMaster.lastUpdatedDate = templateMaster.lastUpdatedDate != null ? moment(templateMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
