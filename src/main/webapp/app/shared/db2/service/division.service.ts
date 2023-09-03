import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IDivision } from 'app/shared/db2/model/division.model';

type EntityArrayResponseType = HttpResponse<IDivision[]>;
@Injectable({ providedIn: 'root' })
export class DivisionService {
  public resourceUrl = SERVER_API_URL + 'api/db2-divisions';

  constructor(protected http: HttpClient) {}

  query(companycode: string): Observable<EntityArrayResponseType> {
    return this.http.get<IDivision[]>(`${this.resourceUrl}/${companycode}`, { observe: 'response' });
  }
}
