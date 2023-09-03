import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDepartmentMaster } from 'app/shared/model/department-master.model';

type EntityResponseType = HttpResponse<IDepartmentMaster>;
type EntityArrayResponseType = HttpResponse<IDepartmentMaster[]>;

@Injectable({ providedIn: 'root' })
export class DepartmentMasterService {
  public resourceUrl = SERVER_API_URL + 'api/department-masters';
  public resourceUrlMs = SERVER_API_URL + 'api/ms-departments';

  constructor(protected http: HttpClient) {}

  create(departmentMaster: IDepartmentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(departmentMaster);
    return this.http
      .post<IDepartmentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(departmentMaster: IDepartmentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(departmentMaster);
    return this.http
      .put<IDepartmentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDepartmentMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDepartmentMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryms(): Observable<EntityArrayResponseType> {
    return this.http
      .get<IDepartmentMaster[]>(this.resourceUrlMs, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(departmentMaster: IDepartmentMaster): IDepartmentMaster {
    const copy: IDepartmentMaster = Object.assign({}, departmentMaster, {
      createdDate:
        departmentMaster.createdDate != null && departmentMaster.createdDate.isValid() ? departmentMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        departmentMaster.lastUpdatedDate != null && departmentMaster.lastUpdatedDate.isValid()
          ? departmentMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((departmentMaster: IDepartmentMaster) => {
        departmentMaster.createdDate = departmentMaster.createdDate != null ? moment(departmentMaster.createdDate) : null;
        departmentMaster.lastUpdatedDate = departmentMaster.lastUpdatedDate != null ? moment(departmentMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
