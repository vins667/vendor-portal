import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsComputation } from 'app/shared/model/tds-computation.model';
import { ITdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';

type EntityResponseType = HttpResponse<ITdsComputation>;
type EntityArrayResponseType = HttpResponse<ITdsComputation[]>;

@Injectable({ providedIn: 'root' })
export class TdsComputationEntryService {
  public resourceUrl = SERVER_API_URL + 'api/tds-computations-entry';
  public resourceUrlReport = SERVER_API_URL + 'api/tds-computation-report-export';

  constructor(protected http: HttpClient) {}

  create(tdsComputation: ITdsComputation): Observable<EntityResponseType> {
    return this.http.post<ITdsComputation>(this.resourceUrl, tdsComputation, { observe: 'response' });
  }

  update(tdsComputation: ITdsComputation): Observable<EntityResponseType> {
    return this.http.put<ITdsComputation>(this.resourceUrl, tdsComputation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITdsComputation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: ITdsDeclarationSearch): Observable<EntityArrayResponseType> {
    return this.http.post<ITdsComputation[]>(this.resourceUrl, req, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  processAll(): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.resourceUrl}-process-all`, { observe: 'response' });
  }

  exportAll(): Observable<Blob> {
    return this.http.post(
      `${this.resourceUrl + '-export'}`,
      { observe: 'response' },
      {
        headers: new HttpHeaders({}),
        responseType: 'blob'
      }
    );
  }

  exportComputation(search: IMasterSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrlReport}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }

  process(id: number): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.resourceUrl}-process/${id}`, { observe: 'response' });
  }
}
