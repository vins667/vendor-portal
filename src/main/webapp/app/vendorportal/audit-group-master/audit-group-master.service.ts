import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IAuditGroupMaster } from 'app/shared/model/audit-group-master.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<IAuditGroupMaster>;
type EntityArrayResponseType = HttpResponse<IAuditGroupMaster[]>;

@Injectable({ providedIn: 'root' })
export class AuditGroupMasterService {
  public resourceUrl = SERVER_API_URL + 'api/audit-group-masters';

  constructor(protected http: HttpClient) {}

  create(auditGroupMaster: IAuditGroupMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditGroupMaster);
    return this.http
      .post<IAuditGroupMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(auditGroupMaster: IAuditGroupMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditGroupMaster);
    return this.http
      .put<IAuditGroupMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAuditGroupMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAuditGroupMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(auditGroupMaster: IAuditGroupMaster): IAuditGroupMaster {
    const copy: IAuditGroupMaster = Object.assign({}, auditGroupMaster, {
      createdDate:
        auditGroupMaster.createdDate != null && auditGroupMaster.createdDate.isValid() ? auditGroupMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        auditGroupMaster.lastUpdatedDate != null && auditGroupMaster.lastUpdatedDate.isValid()
          ? auditGroupMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((auditGroupMaster: IAuditGroupMaster) => {
        auditGroupMaster.createdDate = auditGroupMaster.createdDate != null ? moment(auditGroupMaster.createdDate) : null;
        auditGroupMaster.lastUpdatedDate = auditGroupMaster.lastUpdatedDate != null ? moment(auditGroupMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
