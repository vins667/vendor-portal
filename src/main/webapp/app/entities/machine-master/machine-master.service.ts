import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMachineMaster } from 'app/shared/model/machine-master.model';

type EntityResponseType = HttpResponse<IMachineMaster>;
type EntityArrayResponseType = HttpResponse<IMachineMaster[]>;

@Injectable({ providedIn: 'root' })
export class MachineMasterService {
  public resourceUrl = SERVER_API_URL + 'api/machine-masters';
  constructor(protected http: HttpClient) {}
  create(machineMaster: IMachineMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(machineMaster);
    return this.http
      .post<IMachineMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(machineMaster: IMachineMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(machineMaster);
    return this.http
      .put<IMachineMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMachineMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMachineMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(machineMaster: IMachineMaster): IMachineMaster {
    const copy: IMachineMaster = Object.assign({}, machineMaster, {
      createdDate: machineMaster.createdDate != null && machineMaster.createdDate.isValid() ? machineMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        machineMaster.lastUpdatedDate != null && machineMaster.lastUpdatedDate.isValid() ? machineMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((machineMaster: IMachineMaster) => {
        machineMaster.createdDate = machineMaster.createdDate != null ? moment(machineMaster.createdDate) : null;
        machineMaster.lastUpdatedDate = machineMaster.lastUpdatedDate != null ? moment(machineMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
