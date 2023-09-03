import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';

type EntityResponseType = HttpResponse<IRecruitmentDistrict>;
type EntityArrayResponseType = HttpResponse<IRecruitmentDistrict[]>;

@Injectable({ providedIn: 'root' })
export class RecruitmentDistrictService {
  public resourceUrl = SERVER_API_URL + 'api/recruitment-districts';

  constructor(protected http: HttpClient) {}

  create(recruitmentDistrict: IRecruitmentDistrict): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentDistrict);
    return this.http
      .post<IRecruitmentDistrict>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recruitmentDistrict: IRecruitmentDistrict): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentDistrict);
    return this.http
      .put<IRecruitmentDistrict>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecruitmentDistrict>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecruitmentDistrict[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByState(id?: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IRecruitmentDistrict[]>(`${this.resourceUrl + '-state'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(recruitmentDistrict: IRecruitmentDistrict): IRecruitmentDistrict {
    const copy: IRecruitmentDistrict = Object.assign({}, recruitmentDistrict, {
      createdDate:
        recruitmentDistrict.createdDate != null && recruitmentDistrict.createdDate.isValid()
          ? recruitmentDistrict.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        recruitmentDistrict.lastUpdatedDate != null && recruitmentDistrict.lastUpdatedDate.isValid()
          ? recruitmentDistrict.lastUpdatedDate.toJSON()
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
      res.body.forEach((recruitmentDistrict: IRecruitmentDistrict) => {
        recruitmentDistrict.createdDate = recruitmentDistrict.createdDate != null ? moment(recruitmentDistrict.createdDate) : null;
        recruitmentDistrict.lastUpdatedDate =
          recruitmentDistrict.lastUpdatedDate != null ? moment(recruitmentDistrict.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
