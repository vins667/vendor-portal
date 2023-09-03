import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHardwareQueryBean } from 'app/shared/model/hardware-query.bean.model';
import { map } from 'rxjs/internal/operators/map';

type EntityResponseType = HttpResponse<IHardwareQueryBean>;
type EntityArrayResponseType = HttpResponse<IHardwareQueryBean[]>;

@Injectable({ providedIn: 'root' })
export class HardwareQueryService {
  public resourceUrl = SERVER_API_URL + 'api/asset-audit-details';

  constructor(protected http: HttpClient) {}

  create(hardwareQuery: IHardwareQueryBean): Observable<EntityResponseType> {
    return this.http.post<IHardwareQueryBean>(this.resourceUrl, hardwareQuery, { observe: 'response' });
  }

  update(hardwareQuery: IHardwareQueryBean): Observable<EntityResponseType> {
    return this.http.put<IHardwareQueryBean>(this.resourceUrl, hardwareQuery, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHardwareQueryBean>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHardwareQueryBean[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryCustom(req?: IHardwareQueryBean): Observable<EntityArrayResponseType> {
    return this.http
      .post<IHardwareQueryBean[]>(this.resourceUrl + '-hardware', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    return res;
  }
}
