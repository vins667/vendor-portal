import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserPlantNew, UserPlantNew } from 'app/shared/model/user-plant-new.model';
import { IUserPlantDetailsNew } from 'app/shared/model/user-plant-details-new.model';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import { map } from 'rxjs/operators';

type EntityResponseType = HttpResponse<IUserPlantNew>;
type EntityArrayResponseType = HttpResponse<IUserPlantNew[]>;

@Injectable({ providedIn: 'root' })
export class UserPlantService {
  public resourceUrl = SERVER_API_URL + 'api/user-plants-new';
  public resourceUrlDelRw = SERVER_API_URL + 'api/user-plants-delete';
  public resourceUrlPlant = SERVER_API_URL + 'api/user-plants-all';
  public resourceUrlFilter = SERVER_API_URL + 'api/user-plants-new-filter';

  constructor(protected http: HttpClient) {}

  create(userPlantNew: IUserPlantNew): Observable<EntityResponseType> {
    return this.http.post<IUserPlantNew>(this.resourceUrl, userPlantNew, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IUserPlantNew>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetailRow(ids: string, login: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlDelRw}/${ids}/${login}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserPlantNew[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryFilter(search: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<UserPlantNew[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => res));
  }

  plants(): Observable<HttpResponse<IUserPlantDetailsNew[]>> {
    return this.http.get<IUserPlantDetailsNew[]>(this.resourceUrlPlant, { observe: 'response' });
  }

  delete(ids: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${ids}`, { observe: 'response' });
  }
}
