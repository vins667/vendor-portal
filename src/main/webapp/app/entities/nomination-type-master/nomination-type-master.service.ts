import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INominationTypeMaster } from 'app/shared/model/nomination-type-master.model';

type EntityResponseType = HttpResponse<INominationTypeMaster>;
type EntityArrayResponseType = HttpResponse<INominationTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class NominationTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/nomination-type-masters';

  constructor(protected http: HttpClient) {}

  create(nominationTypeMaster: INominationTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nominationTypeMaster);
    return this.http
      .post<INominationTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(nominationTypeMaster: INominationTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nominationTypeMaster);
    return this.http
      .put<INominationTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INominationTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INominationTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(nominationTypeMaster: INominationTypeMaster): INominationTypeMaster {
    const copy: INominationTypeMaster = Object.assign({}, nominationTypeMaster, {
      createdDate:
        nominationTypeMaster.createdDate != null && nominationTypeMaster.createdDate.isValid()
          ? nominationTypeMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        nominationTypeMaster.lastUpdatedDate != null && nominationTypeMaster.lastUpdatedDate.isValid()
          ? nominationTypeMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((nominationTypeMaster: INominationTypeMaster) => {
        nominationTypeMaster.createdDate = nominationTypeMaster.createdDate != null ? moment(nominationTypeMaster.createdDate) : null;
        nominationTypeMaster.lastUpdatedDate =
          nominationTypeMaster.lastUpdatedDate != null ? moment(nominationTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
