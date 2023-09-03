import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITrimsCreationMaster } from 'app/shared/model/trims-creation-master.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<ITrimsCreationMaster>;
type EntityArrayResponseType = HttpResponse<ITrimsCreationMaster[]>;

@Injectable({ providedIn: 'root' })
export class TrimsCreationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/trims-creation-masters';

  constructor(protected http: HttpClient) {}

  create(trimsCreationMaster: ITrimsCreationMaster): Observable<EntityResponseType> {
    return this.http.post<ITrimsCreationMaster>(this.resourceUrl, trimsCreationMaster, { observe: 'response' });
  }

  update(trimsCreationMaster: ITrimsCreationMaster): Observable<EntityResponseType> {
    return this.http.put<ITrimsCreationMaster>(this.resourceUrl, trimsCreationMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITrimsCreationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrimsCreationMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryCustom(masterSearch?: IMaster): Observable<EntityArrayResponseType> {
    return this.http.post<ITrimsCreationMaster[]>(this.resourceUrl + '-custom', masterSearch, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
