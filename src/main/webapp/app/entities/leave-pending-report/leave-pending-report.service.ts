import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ILeaveSearch } from 'app/shared/model/leave-search.model';

@Injectable({ providedIn: 'root' })
export class LeavePendingReportService {
  public resourceUrl = SERVER_API_URL + 'api/leave-masters';
  constructor(protected http: HttpClient) {}

  downloadPdf(search: ILeaveSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-pending-report'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
