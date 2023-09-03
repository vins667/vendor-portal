import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from 'app/app.constants';
import {IMmrReport} from 'app/shared/model/mmr-report.model';

@Injectable({providedIn: 'root'})
export class MmrReportService {
  public resourceUrl = SERVER_API_URL + 'api/mmr-report';

  constructor(protected http: HttpClient) {
  }

  downloadPdf(search: IMmrReport): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-detail'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
