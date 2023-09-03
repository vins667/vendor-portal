import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPoliciesGroup } from 'app/shared/model/policies-group.model';

type EntityResponseType = HttpResponse<IPoliciesGroup>;
type EntityArrayResponseType = HttpResponse<IPoliciesGroup[]>;

@Injectable({ providedIn: 'root' })
export class PoliciesGroupService {
  public resourceUrl = SERVER_API_URL + 'api/policies-groups';

  constructor(protected http: HttpClient) {}

  create(policiesGroup: IPoliciesGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(policiesGroup);
    return this.http
      .post<IPoliciesGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(policiesGroup: IPoliciesGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(policiesGroup);
    return this.http
      .put<IPoliciesGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPoliciesGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPoliciesGroup[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(policiesGroup: IPoliciesGroup): IPoliciesGroup {
    const copy: IPoliciesGroup = Object.assign({}, policiesGroup, {
      createdDate: policiesGroup.createdDate != null && policiesGroup.createdDate.isValid() ? policiesGroup.createdDate.toJSON() : null,
      lastUpdatedDate:
        policiesGroup.lastUpdatedDate != null && policiesGroup.lastUpdatedDate.isValid() ? policiesGroup.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((policiesGroup: IPoliciesGroup) => {
        policiesGroup.createdDate = policiesGroup.createdDate != null ? moment(policiesGroup.createdDate) : null;
        policiesGroup.lastUpdatedDate = policiesGroup.lastUpdatedDate != null ? moment(policiesGroup.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
