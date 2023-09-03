import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInstituteMaster } from 'app/shared/model/institute-master.model';

type EntityResponseType = HttpResponse<IInstituteMaster>;
type EntityArrayResponseType = HttpResponse<IInstituteMaster[]>;

@Injectable({ providedIn: 'root' })
export class InstituteMasterService {
  public resourceUrl = SERVER_API_URL + 'api/institute-masters';

  constructor(protected http: HttpClient) {}

  create(instituteMaster: IInstituteMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(instituteMaster);
    return this.http
      .post<IInstituteMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(instituteMaster: IInstituteMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(instituteMaster);
    return this.http
      .put<IInstituteMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInstituteMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInstituteMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(instituteMaster: IInstituteMaster): IInstituteMaster {
    const copy: IInstituteMaster = Object.assign({}, instituteMaster, {
      createdDate:
        instituteMaster.createdDate != null && instituteMaster.createdDate.isValid() ? instituteMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        instituteMaster.lastUpdatedDate != null && instituteMaster.lastUpdatedDate.isValid()
          ? instituteMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((instituteMaster: IInstituteMaster) => {
        instituteMaster.createdDate = instituteMaster.createdDate != null ? moment(instituteMaster.createdDate) : null;
        instituteMaster.lastUpdatedDate = instituteMaster.lastUpdatedDate != null ? moment(instituteMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
