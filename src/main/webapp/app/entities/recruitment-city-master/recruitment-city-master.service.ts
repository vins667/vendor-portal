import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';

type EntityResponseType = HttpResponse<IRecruitmentCityMaster>;
type EntityArrayResponseType = HttpResponse<IRecruitmentCityMaster[]>;

@Injectable({ providedIn: 'root' })
export class RecruitmentCityMasterService {
  public resourceUrl = SERVER_API_URL + 'api/recruitment-city-masters';

  constructor(protected http: HttpClient) {}

  create(recruitmentCityMaster: IRecruitmentCityMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentCityMaster);
    return this.http
      .post<IRecruitmentCityMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recruitmentCityMaster: IRecruitmentCityMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentCityMaster);
    return this.http
      .put<IRecruitmentCityMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecruitmentCityMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecruitmentCityMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByDistrict(id?: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IRecruitmentCityMaster[]>(`${this.resourceUrl + '-district'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(recruitmentCityMaster: IRecruitmentCityMaster): IRecruitmentCityMaster {
    const copy: IRecruitmentCityMaster = Object.assign({}, recruitmentCityMaster, {
      createdDate:
        recruitmentCityMaster.createdDate != null && recruitmentCityMaster.createdDate.isValid()
          ? recruitmentCityMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        recruitmentCityMaster.lastUpdatedDate != null && recruitmentCityMaster.lastUpdatedDate.isValid()
          ? recruitmentCityMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((recruitmentCityMaster: IRecruitmentCityMaster) => {
        recruitmentCityMaster.createdDate = recruitmentCityMaster.createdDate != null ? moment(recruitmentCityMaster.createdDate) : null;
        recruitmentCityMaster.lastUpdatedDate =
          recruitmentCityMaster.lastUpdatedDate != null ? moment(recruitmentCityMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
