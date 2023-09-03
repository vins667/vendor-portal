import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IMasterSearch } from 'app/shared/model/master-search.model';

@Injectable({ providedIn: 'root' })
export class TdsComputationReportService {
  public resourceUrl = SERVER_API_URL + 'api/tds-computation-report-export';

  constructor(protected http: HttpClient) {}

  downloadPdf(search: IMasterSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
