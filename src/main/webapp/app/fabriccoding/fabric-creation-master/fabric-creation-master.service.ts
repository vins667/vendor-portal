import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricCreationMaster } from 'app/shared/model/fabric-creation-master.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IFabricCreationMaster>;
type EntityArrayResponseType = HttpResponse<IFabricCreationMaster[]>;

@Injectable({ providedIn: 'root' })
export class FabricCreationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-creation-masters';

  constructor(protected http: HttpClient) {}

  create(fabricCreationMaster: IFabricCreationMaster): Observable<EntityResponseType> {
    return this.http.post<IFabricCreationMaster>(this.resourceUrl, fabricCreationMaster, { observe: 'response' });
  }

  update(fabricCreationMaster: IFabricCreationMaster): Observable<EntityResponseType> {
    return this.http.put<IFabricCreationMaster>(this.resourceUrl, fabricCreationMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFabricCreationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFabricCreationMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryCustom(masterSearch?: IMaster): Observable<EntityArrayResponseType> {
    return this.http.post<IFabricCreationMaster[]>(this.resourceUrl + '-custom', masterSearch, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
