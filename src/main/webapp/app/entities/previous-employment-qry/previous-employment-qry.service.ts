import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IPreviousEmploymentQry } from 'app/shared/model/previous-employment-qry.model';
import { ITdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
type EntityArrayResponseType = HttpResponse<IPreviousEmploymentQry[]>;
type EntityResponseType = HttpResponse<IPreviousEmploymentQry>;
@Injectable({ providedIn: 'root' })
export class PreviousEmploymentQryService {
  public resourceUrl = SERVER_API_URL + 'api/previous-employment-details';
  public resourceUrlActive = SERVER_API_URL + 'api/tds-year-masters-active';

  constructor(protected http: HttpClient) {}

  query(req?: ITdsDeclarationSearch): Observable<EntityArrayResponseType> {
    return this.http.post<IPreviousEmploymentQry[]>(this.resourceUrl + '-qry', req, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPreviousEmploymentQry>(`${this.resourceUrl}-view/${id}`, { observe: 'response' });
  }
}
