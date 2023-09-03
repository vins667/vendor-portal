import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKnitProcessMaster } from 'app/shared/model/knit-process-master.model';

type EntityResponseType = HttpResponse<IKnitProcessMaster>;
type EntityArrayResponseType = HttpResponse<IKnitProcessMaster[]>;

@Injectable({ providedIn: 'root' })
export class KnitProcessMasterService {
  public resourceUrl = SERVER_API_URL + 'api/knit-process-masters';

  constructor(protected http: HttpClient) {}

  create(knitProcessMaster: IKnitProcessMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(knitProcessMaster);
    return this.http
      .post<IKnitProcessMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(knitProcessMaster: IKnitProcessMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(knitProcessMaster);
    return this.http
      .put<IKnitProcessMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IKnitProcessMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IKnitProcessMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(knitProcessMaster: IKnitProcessMaster): IKnitProcessMaster {
    const copy: IKnitProcessMaster = Object.assign({}, knitProcessMaster, {
      createdDate:
        knitProcessMaster.createdDate != null && knitProcessMaster.createdDate.isValid() ? knitProcessMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        knitProcessMaster.lastUpdatedDate != null && knitProcessMaster.lastUpdatedDate.isValid()
          ? knitProcessMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((knitProcessMaster: IKnitProcessMaster) => {
        knitProcessMaster.createdDate = knitProcessMaster.createdDate != null ? moment(knitProcessMaster.createdDate) : null;
        knitProcessMaster.lastUpdatedDate = knitProcessMaster.lastUpdatedDate != null ? moment(knitProcessMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
