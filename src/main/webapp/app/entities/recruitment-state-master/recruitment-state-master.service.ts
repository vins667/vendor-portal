import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';

type EntityResponseType = HttpResponse<IRecruitmentStateMaster>;
type EntityArrayResponseType = HttpResponse<IRecruitmentStateMaster[]>;

@Injectable({ providedIn: 'root' })
export class RecruitmentStateMasterService {
  public resourceUrl = SERVER_API_URL + 'api/recruitment-state-masters';

  constructor(protected http: HttpClient) {}

  create(recruitmentStateMaster: IRecruitmentStateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentStateMaster);
    return this.http
      .post<IRecruitmentStateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recruitmentStateMaster: IRecruitmentStateMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentStateMaster);
    return this.http
      .put<IRecruitmentStateMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecruitmentStateMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecruitmentStateMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByCountry(id?: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IRecruitmentStateMaster[]>(`${this.resourceUrl + '-country'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(recruitmentStateMaster: IRecruitmentStateMaster): IRecruitmentStateMaster {
    const copy: IRecruitmentStateMaster = Object.assign({}, recruitmentStateMaster, {
      createdDate:
        recruitmentStateMaster.createdDate != null && recruitmentStateMaster.createdDate.isValid()
          ? recruitmentStateMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        recruitmentStateMaster.lastUpdatedDate != null && recruitmentStateMaster.lastUpdatedDate.isValid()
          ? recruitmentStateMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((recruitmentStateMaster: IRecruitmentStateMaster) => {
        recruitmentStateMaster.createdDate = recruitmentStateMaster.createdDate != null ? moment(recruitmentStateMaster.createdDate) : null;
        recruitmentStateMaster.lastUpdatedDate =
          recruitmentStateMaster.lastUpdatedDate != null ? moment(recruitmentStateMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
