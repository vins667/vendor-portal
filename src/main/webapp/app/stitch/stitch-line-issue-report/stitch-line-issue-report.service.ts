import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars

import { SERVER_API_URL } from 'app/app.constants';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';

@Injectable({ providedIn: 'root' })
export class StitchLineIssueReportService {
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';
  public resourceUrl = SERVER_API_URL + 'api/stitch-line-issues';
  constructor(protected http: HttpClient) {}

  downloadXlsx(req?: IMasterSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-report'}`, req, { headers: new HttpHeaders({}), responseType: 'blob' });
  }
}
