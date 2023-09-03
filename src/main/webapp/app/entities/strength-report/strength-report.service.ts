import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IStrengthReportBean } from 'app/shared/model/strength-report.model';
import { ISubcomp } from 'app/shared/model/subcomp.model';
import { ISectionMat } from 'app/shared/model/section-mat.model';

@Injectable({ providedIn: 'root' })
export class StrengthReportService {
  public resourceUrl = SERVER_API_URL + 'api/leave-masters';
  public resourceUrlSection = SERVER_API_URL + 'api/section-mat';
  public resourceUrlSubComp = SERVER_API_URL + 'api/subcomp';

  constructor(protected http: HttpClient) {}

  findSubComp(id: string): Observable<HttpResponse<ISubcomp[]>> {
    return this.http.get<ISubcomp[]>(`${this.resourceUrlSubComp}/${id}`, { observe: 'response' });
  }

  findLine(id: string): Observable<HttpResponse<ISectionMat[]>> {
    return this.http.get<ISectionMat[]>(`${this.resourceUrlSection}/${id}`, { observe: 'response' });
  }

  downloadPdf(search: IStrengthReportBean): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-strength-report'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }

  downloadCTCPdf(search: IStrengthReportBean): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-ctc-report'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }

  downloadCTCDetailsPdf(search: IStrengthReportBean): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-ctc-detail-report'}`, search, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
