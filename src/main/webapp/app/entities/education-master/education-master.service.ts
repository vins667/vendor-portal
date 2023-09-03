import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEducationMaster } from 'app/shared/model/education-master.model';

type EntityResponseType = HttpResponse<IEducationMaster>;
type EntityArrayResponseType = HttpResponse<IEducationMaster[]>;

@Injectable({ providedIn: 'root' })
export class EducationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/education-masters';

  constructor(protected http: HttpClient) {}

  create(educationMaster: IEducationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(educationMaster);
    return this.http
      .post<IEducationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(educationMaster: IEducationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(educationMaster);
    return this.http
      .put<IEducationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEducationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEducationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(educationMaster: IEducationMaster): IEducationMaster {
    const copy: IEducationMaster = Object.assign({}, educationMaster, {
      createdDate:
        educationMaster.createdDate != null && educationMaster.createdDate.isValid() ? educationMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        educationMaster.lastUpdatedDate != null && educationMaster.lastUpdatedDate.isValid()
          ? educationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((educationMaster: IEducationMaster) => {
        educationMaster.createdDate = educationMaster.createdDate != null ? moment(educationMaster.createdDate) : null;
        educationMaster.lastUpdatedDate = educationMaster.lastUpdatedDate != null ? moment(educationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
