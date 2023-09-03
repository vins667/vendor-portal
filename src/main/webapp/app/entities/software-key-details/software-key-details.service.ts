import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISoftwareKeyDetailsBean } from 'app/shared/model/software-key-details-bean.model';
import { map } from 'rxjs/operators';

type EntityResponseType = HttpResponse<ISoftwareKeyDetailsBean>;
type EntityArrayResponseType = HttpResponse<ISoftwareKeyDetailsBean[]>;

@Injectable({ providedIn: 'root' })
export class SoftwareKeyDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/asset-audit-details';

  constructor(protected http: HttpClient) {}

  create(softwareKeyDetails: ISoftwareKeyDetailsBean): Observable<EntityResponseType> {
    return this.http.post<ISoftwareKeyDetailsBean>(this.resourceUrl, softwareKeyDetails, { observe: 'response' });
  }

  update(softwareKeyDetails: ISoftwareKeyDetailsBean): Observable<EntityResponseType> {
    return this.http.put<ISoftwareKeyDetailsBean>(this.resourceUrl, softwareKeyDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISoftwareKeyDetailsBean>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISoftwareKeyDetailsBean[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryCustom(req?: ISoftwareKeyDetailsBean): Observable<EntityArrayResponseType> {
    return this.http
      .post<ISoftwareKeyDetailsBean[]>(this.resourceUrl + '-key', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    return res;
  }
}
