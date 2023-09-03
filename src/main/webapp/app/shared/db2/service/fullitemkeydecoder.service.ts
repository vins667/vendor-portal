import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';

type EntityArrayResponseType = HttpResponse<IFullitemkeydecoder[]>;
@Injectable({ providedIn: 'root' })
export class FullitemkeydecoderService {
  public resourceUrl = SERVER_API_URL + 'api/fullitemkeydecoders';

  constructor(protected http: HttpClient) {}

  query(companycode: string): Observable<EntityArrayResponseType> {
    return this.http.get<IFullitemkeydecoder[]>(`${this.resourceUrl}/${companycode}`, { observe: 'response' });
  }

  find(fullitemkeydecoder: IFullitemkeydecoder): Observable<HttpResponse<IFullitemkeydecoder>> {
    return this.http.post<IFullitemkeydecoder>(`${this.resourceUrl}`, fullitemkeydecoder, { observe: 'response' });
  }
}
