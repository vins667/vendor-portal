import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IVcutFactorySummary } from 'app/shared/model/vcut-factory-summary.model';
import { IMaster } from 'app/shared/model/master.modal';
import { IVcutTvImageSummary } from 'app/shared/model/vcut-tv-image-summary.model';

type EntityResponseType = HttpResponse<IVcutFactorySummary>;
type EntityArrayResponseType = HttpResponse<IVcutFactorySummary[]>;

@Injectable({ providedIn: 'root' })
export class VcutLineSummaryService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-line-summaries';
  public resourceUrlStyle = SERVER_API_URL + 'api/vcut-line-images-summaries';

  constructor(protected http: HttpClient) {}

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IVcutFactorySummary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findDefects(id: string): Observable<HttpResponse<IVcutTvImageSummary>> {
    return this.http.get<IVcutTvImageSummary>(`${this.resourceUrlStyle}/${id}`, { observe: 'response' });
  }

  findWithDate(master: IMaster): Observable<EntityResponseType> {
    return this.http.post<IVcutFactorySummary>(`${this.resourceUrl}`, master, { observe: 'response' });
  }

  query(): Observable<EntityResponseType> {
    return this.http.get<IVcutFactorySummary>(this.resourceUrl, { observe: 'response' });
  }
}
