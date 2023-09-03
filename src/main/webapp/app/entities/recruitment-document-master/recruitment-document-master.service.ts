import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';

type EntityResponseType = HttpResponse<IRecruitmentDocumentMaster>;
type EntityArrayResponseType = HttpResponse<IRecruitmentDocumentMaster[]>;

@Injectable({ providedIn: 'root' })
export class RecruitmentDocumentMasterService {
  public resourceUrl = SERVER_API_URL + 'api/recruitment-document-masters';

  constructor(protected http: HttpClient) {}

  create(recruitmentDocumentMaster: IRecruitmentDocumentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentDocumentMaster);
    return this.http
      .post<IRecruitmentDocumentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recruitmentDocumentMaster: IRecruitmentDocumentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recruitmentDocumentMaster);
    return this.http
      .put<IRecruitmentDocumentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecruitmentDocumentMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecruitmentDocumentMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryByType(req: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IRecruitmentDocumentMaster[]>(`${this.resourceUrl + '-type'}/${req}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(recruitmentDocumentMaster: IRecruitmentDocumentMaster): IRecruitmentDocumentMaster {
    const copy: IRecruitmentDocumentMaster = Object.assign({}, recruitmentDocumentMaster, {
      createdDate:
        recruitmentDocumentMaster.createdDate != null && recruitmentDocumentMaster.createdDate.isValid()
          ? recruitmentDocumentMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        recruitmentDocumentMaster.lastUpdatedDate != null && recruitmentDocumentMaster.lastUpdatedDate.isValid()
          ? recruitmentDocumentMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((recruitmentDocumentMaster: IRecruitmentDocumentMaster) => {
        recruitmentDocumentMaster.createdDate =
          recruitmentDocumentMaster.createdDate != null ? moment(recruitmentDocumentMaster.createdDate) : null;
        recruitmentDocumentMaster.lastUpdatedDate =
          recruitmentDocumentMaster.lastUpdatedDate != null ? moment(recruitmentDocumentMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
