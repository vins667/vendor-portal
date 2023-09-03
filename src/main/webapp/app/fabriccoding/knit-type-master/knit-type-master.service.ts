import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKnitTypeMaster } from 'app/shared/model/knit-type-master.model';

type EntityResponseType = HttpResponse<IKnitTypeMaster>;
type EntityArrayResponseType = HttpResponse<IKnitTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class KnitTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/knit-type-masters';

  constructor(protected http: HttpClient) {}

  create(knitTypeMaster: IKnitTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(knitTypeMaster);
    return this.http
      .post<IKnitTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(knitTypeMaster: IKnitTypeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(knitTypeMaster);
    return this.http
      .put<IKnitTypeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IKnitTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IKnitTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(knitTypeMaster: IKnitTypeMaster): IKnitTypeMaster {
    const copy: IKnitTypeMaster = Object.assign({}, knitTypeMaster, {
      createdDate: knitTypeMaster.createdDate != null && knitTypeMaster.createdDate.isValid() ? knitTypeMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        knitTypeMaster.lastUpdatedDate != null && knitTypeMaster.lastUpdatedDate.isValid() ? knitTypeMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((knitTypeMaster: IKnitTypeMaster) => {
        knitTypeMaster.createdDate = knitTypeMaster.createdDate != null ? moment(knitTypeMaster.createdDate) : null;
        knitTypeMaster.lastUpdatedDate = knitTypeMaster.lastUpdatedDate != null ? moment(knitTypeMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
