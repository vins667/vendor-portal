import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsComputation } from 'app/shared/model/tds-computation.model';

type EntityResponseType = HttpResponse<ITdsComputation>;
type EntityArrayResponseType = HttpResponse<ITdsComputation[]>;

@Injectable({ providedIn: 'root' })
export class TdsComputationService {
  public resourceUrl = SERVER_API_URL + 'api/tds-computations';

  constructor(protected http: HttpClient) {}

  find(): Observable<EntityResponseType> {
    return this.http.get<ITdsComputation>(`${this.resourceUrl}`, { observe: 'response' });
  }

  process(id: number): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.resourceUrl}-entry-process/${id}`, { observe: 'response' });
  }
}
