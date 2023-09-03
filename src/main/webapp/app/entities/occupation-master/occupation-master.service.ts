import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOccupationMaster } from 'app/shared/model/occupation-master.model';

type EntityResponseType = HttpResponse<IOccupationMaster>;
type EntityArrayResponseType = HttpResponse<IOccupationMaster[]>;

@Injectable({ providedIn: 'root' })
export class OccupationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/occupation-masters';

  constructor(protected http: HttpClient) {}

  create(occupationMaster: IOccupationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(occupationMaster);
    return this.http
      .post<IOccupationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(occupationMaster: IOccupationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(occupationMaster);
    return this.http
      .put<IOccupationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOccupationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOccupationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(occupationMaster: IOccupationMaster): IOccupationMaster {
    const copy: IOccupationMaster = Object.assign({}, occupationMaster, {
      createdDate:
        occupationMaster.createdDate != null && occupationMaster.createdDate.isValid() ? occupationMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        occupationMaster.lastUpdatedDate != null && occupationMaster.lastUpdatedDate.isValid()
          ? occupationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((occupationMaster: IOccupationMaster) => {
        occupationMaster.createdDate = occupationMaster.createdDate != null ? moment(occupationMaster.createdDate) : null;
        occupationMaster.lastUpdatedDate = occupationMaster.lastUpdatedDate != null ? moment(occupationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
