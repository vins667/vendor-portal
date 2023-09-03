import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { Master } from 'app/shared/model/master.modal';
import { ICutIssueStitchDetails } from 'app/shared/model/cut-issue-stitch-details.model';
import { ICutIssueBean } from 'app/shared/model/cut-issue-bean.model';
import { ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';

type EntityResponseType = HttpResponse<ICutPlanIssueStitch>;
type EntityArrayResponseType = HttpResponse<ICutPlanIssueStitch[]>;

@Injectable({ providedIn: 'root' })
export class CutPlanIssueStitchService {
  public resourceUrl = SERVER_API_URL + 'api/cut-plan-issue-stitches';
  public resourceUrlDetails = SERVER_API_URL + 'api/cut-issue-stitch-details';
  public resourceUrlFilter = SERVER_API_URL + 'api/cut-plan-issue-stitches-filter';
  public resourceUrlStyle = SERVER_API_URL + 'api/db2-salesorders-style';
  public resourceUrlBundle = SERVER_API_URL + 'api/cut-plan-bundles';
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';
  public resourceUrlPdf = SERVER_API_URL + 'api/cut-plan-issue-stich-pdf';

  constructor(protected http: HttpClient) {}

  create(cutPlanIssueStitch: ICutPlanIssueStitch): Observable<EntityResponseType> {
    return this.http
      .post<ICutPlanIssueStitch>(this.resourceUrl, cutPlanIssueStitch, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cutPlanIssueStitch: ICutPlanIssueStitch): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cutPlanIssueStitch);
    return this.http
      .put<ICutPlanIssueStitch>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutPlanIssueStitch>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  post(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutPlanIssueStitch>(`${this.resourceUrl + '-post'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  bundle(id: number): Observable<HttpResponse<ICutIssueStitchDetails>> {
    return this.http.get<ICutIssueStitchDetails>(`${this.resourceUrlBundle}/${id}`, { observe: 'response' });
  }

  style(projectcode: string): Observable<HttpResponse<ICutIssueBean>> {
    const search = new Master();
    search.code = projectcode;
    return this.http.post<ICutIssueBean>(`${this.resourceUrlStyle}`, search, { observe: 'response' });
  }

  downloadPdf(id: number): Observable<Blob> {
    return this.http.get(`${this.resourceUrlPdf}/${id}`, { responseType: 'blob' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICutPlanIssueStitch[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: ICutPlanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICutPlanIssueStitch[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetails(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(cutPlanIssueStitch: ICutPlanIssueStitch): ICutPlanIssueStitch {
    const copy: ICutPlanIssueStitch = Object.assign({}, cutPlanIssueStitch, {
      createddate:
        cutPlanIssueStitch.createddate && cutPlanIssueStitch.createddate.isValid() ? cutPlanIssueStitch.createddate.toJSON() : undefined,
      lastupdateddate:
        cutPlanIssueStitch.lastupdateddate && cutPlanIssueStitch.lastupdateddate.isValid()
          ? cutPlanIssueStitch.lastupdateddate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate ? moment(res.body.createddate) : undefined;
      res.body.lastupdateddate = res.body.lastupdateddate ? moment(res.body.lastupdateddate) : undefined;
      res.body.postedDate = res.body.postedDate ? moment(res.body.postedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cutPlanIssueStitch: ICutPlanIssueStitch) => {
        cutPlanIssueStitch.createddate = cutPlanIssueStitch.createddate ? moment(cutPlanIssueStitch.createddate) : undefined;
        cutPlanIssueStitch.lastupdateddate = cutPlanIssueStitch.lastupdateddate ? moment(cutPlanIssueStitch.lastupdateddate) : undefined;
      });
    }
    return res;
  }
}
