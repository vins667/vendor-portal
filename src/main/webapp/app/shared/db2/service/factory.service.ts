import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IFactory } from 'app/shared/model/factory.model';

type EntityArrayResponseType = HttpResponse<IFactory[]>;
@Injectable({ providedIn: 'root' })
export class FactoryService {
  public resourceUrl = SERVER_API_URL + 'api/db2-factories-by-division';

  constructor(protected http: HttpClient) {}

  byDivision(divisioncode?: string): Observable<EntityArrayResponseType> {
    return this.http.get<IFactory[]>(`${this.resourceUrl}/${divisioncode}`, { observe: 'response' });
  }
}
