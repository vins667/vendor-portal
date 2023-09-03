import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IOrderpartnertds } from 'app/shared/db2/model/orderpartnertds.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityArrayResponseType = HttpResponse<IOrderpartnertds[]>;
@Injectable({ providedIn: 'root' })
export class OrderpartnertdsService {
  public resourceUrl = SERVER_API_URL + 'api/db2-orderpartnertds';

  constructor(protected http: HttpClient) {}

  tds(master: IMaster): Observable<EntityArrayResponseType> {
    return this.http.post<IOrderpartnertds[]>(`${this.resourceUrl}`, master, { observe: 'response' });
  }
}
