import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISoftwareQueryBean } from 'app/shared/model/software-query-bean.model';
import { map } from 'rxjs/operators';

type EntityResponseType = HttpResponse<ISoftwareQueryBean>;
type EntityArrayResponseType = HttpResponse<ISoftwareQueryBean[]>;

@Injectable({ providedIn: 'root' })
export class SoftwareQueryService {
  public resourceUrl = SERVER_API_URL + 'api/asset-audit-details';

  constructor(protected http: HttpClient) {}

  create(softwareQuery: ISoftwareQueryBean): Observable<EntityResponseType> {
    return this.http.post<ISoftwareQueryBean>(this.resourceUrl, softwareQuery, { observe: 'response' });
  }

  update(softwareQuery: ISoftwareQueryBean): Observable<EntityResponseType> {
    return this.http.put<ISoftwareQueryBean>(this.resourceUrl, softwareQuery, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISoftwareQueryBean>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISoftwareQueryBean[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryCustom(req?: ISoftwareQueryBean): Observable<EntityArrayResponseType> {
    return this.http
      .post<ISoftwareQueryBean[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    return res;
  }
}
