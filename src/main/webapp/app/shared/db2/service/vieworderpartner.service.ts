import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IMaster } from 'app/shared/model/master.modal';

@Injectable({ providedIn: 'root' })
export class VieworderpartnerService {
  public resourceUrl = SERVER_API_URL + 'api/vieworderpartners-state';

  constructor(protected http: HttpClient) {}

  state(numberId: number): Observable<HttpResponse<IMaster>> {
    return this.http.get<IMaster>(`${this.resourceUrl}/${numberId}`, { observe: 'response' });
  }
}
