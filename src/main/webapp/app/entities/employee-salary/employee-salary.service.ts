import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IMasterSearch } from 'app/shared/model/master-search.model';
import { ISalarySearch } from 'app/shared/model/salary-search.model';

@Injectable({ providedIn: 'root' })
export class EmployeeSalaryService {
  public resourceUrl = SERVER_API_URL + 'api/employee-salary';

  constructor(protected http: HttpClient) {}

  downloadPdf(search: ISalarySearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
