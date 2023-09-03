import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { ICutFabricIssue } from 'app/shared/model/cut-fabric-issue.model';
import { ICutPlanEntryDetails } from 'app/shared/model/cut-plan-entry-details.model';
import { IBalance } from 'app/shared/db2/model/balance.model';

type EntityResponseType = HttpResponse<ICutFabricIssue>;
type EntityArrayResponseType = HttpResponse<ICutFabricIssue[]>;

@Injectable({ providedIn: 'root' })
export class CutFabricIssueService {
  public resourceUrl = SERVER_API_URL + 'api/cut-fabric-issue-detail';
  public resourceUrlPdf = SERVER_API_URL + 'api/cut-plan-entries-issue-pdf';
  public resourceUrlSave = SERVER_API_URL + 'api/cut-fabric-issue-detail-save';
  public resourceUrlSplitted = SERVER_API_URL + 'api/splitfabricstock';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutFabricIssue>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  create(cutPlanEntryDetails: ICutPlanEntryDetails[]): Observable<EntityResponseType> {
    return this.http
      .post<ICutFabricIssue>(this.resourceUrlSave, cutPlanEntryDetails, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  downloadPdf(id: number): Observable<Blob> {
    return this.http.get(`${this.resourceUrlPdf}/${id}`, { responseType: 'blob' });
  }

  pushSplitted(cutPlanEntryDetails: ICutPlanEntryDetails): Observable<HttpResponse<IBalance[]>> {
    return this.http.post<IBalance[]>(this.resourceUrlSplitted, cutPlanEntryDetails, { observe: 'response' });
  }

  protected convertDateFromClient(cutFabricIssue: ICutFabricIssue): ICutFabricIssue {
    const copy: ICutFabricIssue = Object.assign({}, cutFabricIssue, {
      createddate: cutFabricIssue.createddate != null && cutFabricIssue.createddate.isValid() ? cutFabricIssue.createddate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate != null ? moment(res.body.createddate) : null;
    }
    return res;
  }
}
