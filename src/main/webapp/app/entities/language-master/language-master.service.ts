import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILanguageMaster } from 'app/shared/model/language-master.model';

type EntityResponseType = HttpResponse<ILanguageMaster>;
type EntityArrayResponseType = HttpResponse<ILanguageMaster[]>;

@Injectable({ providedIn: 'root' })
export class LanguageMasterService {
  public resourceUrl = SERVER_API_URL + 'api/language-masters';

  constructor(protected http: HttpClient) {}

  create(languageMaster: ILanguageMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(languageMaster);
    return this.http
      .post<ILanguageMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(languageMaster: ILanguageMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(languageMaster);
    return this.http
      .put<ILanguageMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILanguageMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILanguageMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(languageMaster: ILanguageMaster): ILanguageMaster {
    const copy: ILanguageMaster = Object.assign({}, languageMaster, {
      createdDate: languageMaster.createdDate != null && languageMaster.createdDate.isValid() ? languageMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        languageMaster.lastUpdatedDate != null && languageMaster.lastUpdatedDate.isValid() ? languageMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((languageMaster: ILanguageMaster) => {
        languageMaster.createdDate = languageMaster.createdDate != null ? moment(languageMaster.createdDate) : null;
        languageMaster.lastUpdatedDate = languageMaster.lastUpdatedDate != null ? moment(languageMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
