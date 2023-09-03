import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { IDayStatus } from 'app/shared/model/day-status.model';

type EntityResponseType = HttpResponse<IDayStatus>;

@Injectable({ providedIn: 'root' })
export class DayStatusService {
  public resourceUrl = SERVER_API_URL + 'api/day-status';

  constructor(protected http: HttpClient) {}

  find(dayNo: any): Observable<EntityResponseType> {
    return this.http.get<IDayStatus>(`${this.resourceUrl}/${dayNo}`, { observe: 'response' });
  }
}
