import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ISalarySearch } from 'app/shared/model/salary-search.model';

@Injectable({ providedIn: 'root' })
export class MonthlyReportService {
  public resourceUrl = SERVER_API_URL + 'api/monthly-salary';

  constructor(protected http: HttpClient) {}

  downloadPdf(search: ISalarySearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-report-summary'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
