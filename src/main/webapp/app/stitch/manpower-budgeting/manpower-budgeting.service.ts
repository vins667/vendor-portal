import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IManpowerBudgeting } from './manpower-budgeting.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { SERVER_API_URL } from 'app/app.constants';
import { IManpowerSearch } from 'app/shared/model/manpower-search.model';
import { IManpowerBean } from 'app/shared/model/manpower-bean.model';
import { Resources } from 'app/shared/model/resources.model';
import { IManpowerTypeMaster } from 'app/shared/model/manpower-type-master.model';
import { IMaster } from 'app/shared/model/master.modal';

export type EntityResponseType = HttpResponse<IManpowerBudgeting>;
export type EntityArrayResponseType = HttpResponse<IManpowerBudgeting[]>;

@Injectable({ providedIn: 'root' })
export class ManpowerBudgetingService {
  public resourceUrl = SERVER_API_URL + 'api/manpower-budgetings';
  public resourceUrlManpower = SERVER_API_URL + 'api/fetch-manpowers-details';
  public resourceUrlPlantCode = SERVER_API_URL + 'api/resources-byplantcode';
  public resourceUrlType = SERVER_API_URL + 'api/manpower-type-masters';

  constructor(protected http: HttpClient) {}

  create(manpowerBudgeting: IManpowerBudgeting): Observable<EntityResponseType> {
    return this.http.post<IManpowerBudgeting>(this.resourceUrl, manpowerBudgeting, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IManpowerBudgeting>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IManpowerBudgeting[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  types(): Observable<HttpResponse<IManpowerTypeMaster[]>> {
    return this.http.get<IManpowerTypeMaster[]>(this.resourceUrlType, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  fetch(search: IManpowerSearch): Observable<HttpResponse<IManpowerBean[]>> {
    return this.http.post<IManpowerBean[]>(`${this.resourceUrlManpower}`, search, { observe: 'response' });
  }

  resourcesByPlantCode(master: IMaster): Observable<HttpResponse<Resources[]>> {
    return this.http.post<Resources[]>(`${this.resourceUrlPlantCode}`, master, { observe: 'response' });
  }
}
