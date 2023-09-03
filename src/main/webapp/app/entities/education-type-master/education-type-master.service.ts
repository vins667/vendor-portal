import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEducationTypeMaster } from 'app/shared/model/education-type-master.model';

type EntityResponseType = HttpResponse<IEducationTypeMaster>;
type EntityArrayResponseType = HttpResponse<IEducationTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class EducationTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/education-type-masters';

  constructor(protected http: HttpClient) {}

  create(educationTypeMaster: IEducationTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(educationTypeMaster);
    return this.http
      .post<IEducationTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(educationTypeMaster: IEducationTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(educationTypeMaster);
    return this.http
      .put<IEducationTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEducationTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEducationTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(educationTypeMaster: IEducationTypeMaster): IEducationTypeMaster {
    const copy: IEducationTypeMaster = Object.assign({}, educationTypeMaster, {
      createdDate:
        educationTypeMaster.createdDate != null && educationTypeMaster.createdDate.isValid()
          ? educationTypeMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        educationTypeMaster.lastUpdatedDate != null && educationTypeMaster.lastUpdatedDate.isValid()
          ? educationTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((educationTypeMaster: IEducationTypeMaster) => {
        educationTypeMaster.createdDate = educationTypeMaster.createdDate != null ? moment(educationTypeMaster.createdDate) : null;
        educationTypeMaster.lastUpdatedDate =
          educationTypeMaster.lastUpdatedDate != null ? moment(educationTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
