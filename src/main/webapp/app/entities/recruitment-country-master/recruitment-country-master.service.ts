import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';

type EntityResponseType = HttpResponse<IRecruitmentCountryMaster>;
type EntityArrayResponseType = HttpResponse<IRecruitmentCountryMaster[]>;

@Injectable({ providedIn: 'root' })
export class RecruitmentCountryMasterService {
  public resourceUrl = SERVER_API_URL + 'api/recruitment-country-masters';

  constructor(protected http: HttpClient) {}

  create(recruitmentCountryMaster: IRecruitmentCountryMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentCountryMaster);
    return this.http
      .post<IRecruitmentCountryMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recruitmentCountryMaster: IRecruitmentCountryMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentCountryMaster);
    return this.http
      .put<IRecruitmentCountryMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecruitmentCountryMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecruitmentCountryMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(recruitmentCountryMaster: IRecruitmentCountryMaster): IRecruitmentCountryMaster {
    const copy: IRecruitmentCountryMaster = Object.assign({}, recruitmentCountryMaster, {
      createdDate:
        recruitmentCountryMaster.createdDate != null && recruitmentCountryMaster.createdDate.isValid()
          ? recruitmentCountryMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        recruitmentCountryMaster.lastUpdatedDate != null && recruitmentCountryMaster.lastUpdatedDate.isValid()
          ? recruitmentCountryMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((recruitmentCountryMaster: IRecruitmentCountryMaster) => {
        recruitmentCountryMaster.createdDate =
          recruitmentCountryMaster.createdDate != null ? moment(recruitmentCountryMaster.createdDate) : null;
        recruitmentCountryMaster.lastUpdatedDate =
          recruitmentCountryMaster.lastUpdatedDate != null ? moment(recruitmentCountryMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
