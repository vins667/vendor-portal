import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrganizationType } from 'app/shared/model/organization-type.model';

type EntityResponseType = HttpResponse<IOrganizationType>;
type EntityArrayResponseType = HttpResponse<IOrganizationType[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationTypeService {
  public resourceUrl = SERVER_API_URL + 'api/organization-types';

  constructor(protected http: HttpClient) {}

  create(organizationType: IOrganizationType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(organizationType);
    return this.http
      .post<IOrganizationType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(organizationType: IOrganizationType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(organizationType);
    return this.http
      .put<IOrganizationType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOrganizationType>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrganizationType[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(organizationType: IOrganizationType): IOrganizationType {
    const copy: IOrganizationType = Object.assign({}, organizationType, {
      createdDate:
        organizationType.createdDate != null && organizationType.createdDate.isValid() ? organizationType.createdDate.toJSON() : null,
      lastUpdatedDate:
        organizationType.lastUpdatedDate != null && organizationType.lastUpdatedDate.isValid()
          ? organizationType.lastUpdatedDate.toJSON()
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
      res.body.forEach((organizationType: IOrganizationType) => {
        organizationType.createdDate = organizationType.createdDate != null ? moment(organizationType.createdDate) : null;
        organizationType.lastUpdatedDate = organizationType.lastUpdatedDate != null ? moment(organizationType.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
