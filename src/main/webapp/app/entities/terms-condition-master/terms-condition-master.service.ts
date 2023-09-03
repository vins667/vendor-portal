import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { ITermsConditionMaster } from 'app/shared/model/terms-condition-master.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<ITermsConditionMaster>;
type EntityArrayResponseType = HttpResponse<ITermsConditionMaster[]>;

@Injectable({ providedIn: 'root' })
export class TermsConditionMasterService {
  public resourceUrl = SERVER_API_URL + 'api/terms-condition-masters';
  public resourceDetalsUrl = SERVER_API_URL + 'api/terms-condition-details';

  constructor(protected http: HttpClient) {}

  create(termsConditionMaster: ITermsConditionMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(termsConditionMaster);
    return this.http
      .post<ITermsConditionMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(termsConditionMaster: ITermsConditionMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(termsConditionMaster);
    return this.http
      .put<ITermsConditionMaster>(this.resourceUrl + '-update', copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITermsConditionMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITermsConditionMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceDetalsUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(termsConditionMaster: ITermsConditionMaster): ITermsConditionMaster {
    const copy: ITermsConditionMaster = Object.assign({}, termsConditionMaster, {
      createdDate:
        termsConditionMaster.createdDate != null && termsConditionMaster.createdDate.isValid()
          ? termsConditionMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        termsConditionMaster.lastUpdatedDate != null && termsConditionMaster.lastUpdatedDate.isValid()
          ? termsConditionMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((termsConditionMaster: ITermsConditionMaster) => {
        termsConditionMaster.createdDate = termsConditionMaster.createdDate != null ? moment(termsConditionMaster.createdDate) : null;
        termsConditionMaster.lastUpdatedDate =
          termsConditionMaster.lastUpdatedDate != null ? moment(termsConditionMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
