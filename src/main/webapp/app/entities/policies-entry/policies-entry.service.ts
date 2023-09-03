import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPolicies } from 'app/shared/model/policies.model';
import { IMessage } from 'app/shared/model/message.model';

type EntityResponseType = HttpResponse<IPolicies>;
type EntityArrayResponseType = HttpResponse<IPolicies[]>;

@Injectable({ providedIn: 'root' })
export class PoliciesEntryService {
  public resourceUrl = SERVER_API_URL + 'api/policies';

  constructor(protected http: HttpClient) {}

  create(file: File, policies: IPolicies): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('policyName', policies.policyName);
    formData.append('policyGroupId', policies.policiesGroup.id + '');
    return this.http
      .post<IPolicies>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(file: File, policies: IPolicies): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('policyName', policies.policyName);
    formData.append('policyGroupId', policies.policiesGroup.id + '');
    formData.append('id', policies.id + '');
    return this.http
      .post<IPolicies>(this.resourceUrl + '-update', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  updateMultiple(iPolicies: IPolicies[]): Observable<HttpResponse<IMessage>> {
    const iPoliciesData = [];
    iPolicies.forEach(policies => {
      const copy = this.convertDateFromClient(policies);
      iPoliciesData.push(copy);
    });

    return this.http.post<IMessage>(this.resourceUrl + '-multiple', iPoliciesData, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPolicies>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPolicies[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryOrdered(): Observable<EntityArrayResponseType> {
    return this.http
      .get<IPolicies[]>(this.resourceUrl + '-ordered', { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryByProductGroup(req?: any): Observable<EntityArrayResponseType> {
    return this.http
      .get<IPolicies[]>(`${this.resourceUrl + '-by-group'}/${req}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(policies: IPolicies): IPolicies {
    const copy: IPolicies = Object.assign({}, policies, {
      createdDate: policies.createdDate != null && policies.createdDate.isValid() ? policies.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((policies: IPolicies) => {
        policies.createdDate = policies.createdDate != null ? moment(policies.createdDate) : null;
      });
    }
    return res;
  }
}
