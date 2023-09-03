import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { ICutIssueStitchDetails } from 'app/shared/model/cut-issue-stitch-details.model';
import { ICutIssueBean } from 'app/shared/model/cut-issue-bean.model';
import { ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';
import { Resources } from 'app/shared/model/resources.model';
import { IStitchLineIssue } from 'app/shared/model/stitch-line-issue.model';
import { IProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { IStitchLineIssueDetails } from 'app/shared/model/stitch-line-issue-details.model';

type EntityResponseType = HttpResponse<IStitchLineIssue>;
type EntityArrayResponseType = HttpResponse<IStitchLineIssue[]>;

@Injectable({ providedIn: 'root' })
export class StitchLineIssueService {
  public resourceUrl = SERVER_API_URL + 'api/stitch-line-issues';
  public resourceUrlDetails = SERVER_API_URL + 'api/stitch-line-issue-details';
  public resourceUrlFilter = SERVER_API_URL + 'api/stitch-line-issues-filter';
  public resourceUrlStyle = SERVER_API_URL + 'api/db2-salesorders-style';
  public resourceUrlBundle = SERVER_API_URL + 'api/cut-plan-bundles-stitch';
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';
  public resourceUrlPlantCode = SERVER_API_URL + 'api/sewing-resources-byplantcode';
  public resourceUrlOrders = SERVER_API_URL + 'api/productionorders-stitch';
  public resourceUrlDb2Project = SERVER_API_URL + 'api/db2-productionorder-project';
  public resourceUrlPdf = SERVER_API_URL + 'api/stitch-line-issues-pdf';

  constructor(protected http: HttpClient) {}

  create(stitchLineIssue: IStitchLineIssue): Observable<EntityResponseType> {
    return this.http
      .post<IStitchLineIssue>(this.resourceUrl, stitchLineIssue, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(stitchLineIssue: IStitchLineIssue): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stitchLineIssue);
    return this.http
      .put<IStitchLineIssue>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  transfer(stitchLineIssueDetail: IStitchLineIssueDetails): Observable<HttpResponse<IStitchLineIssueDetails>> {
    return this.http.post<IStitchLineIssueDetails>(this.resourceUrl + '-transfer', stitchLineIssueDetail, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStitchLineIssue>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  post(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStitchLineIssue>(`${this.resourceUrl + '-post'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  bundle(search: ICutPlanSearch): Observable<HttpResponse<ICutIssueStitchDetails>> {
    return this.http.post<ICutIssueStitchDetails>(`${this.resourceUrlBundle}`, search, { observe: 'response' });
  }

  resourcesByPlantCode(plantCode: string): Observable<HttpResponse<Resources[]>> {
    return this.http.get<Resources[]>(`${this.resourceUrlPlantCode}/${plantCode}`, { observe: 'response' });
  }

  downloadPdf(id: number): Observable<Blob> {
    return this.http.get(`${this.resourceUrlPdf}/${id}`, { responseType: 'blob' });
  }

  style(projectcode: string): Observable<HttpResponse<ICutIssueBean>> {
    const search = new Master();
    search.code = projectcode;
    return this.http.post<ICutIssueBean>(`${this.resourceUrlStyle}`, search, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IStitchLineIssue[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: ICutPlanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IStitchLineIssue[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(search: IProductionorderSearch): Observable<HttpResponse<IProductionorder[]>> {
    return this.http.post<IProductionorder[]>(this.resourceUrlOrders, search, { observe: 'response' });
  }

  getAllDetailByPo(ponumber: string): Observable<HttpResponse<IMaster>> {
    const master = new Master();
    master.name = ponumber;
    return this.http.post<IMaster>(this.resourceUrlDb2Project, master, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetails(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(stitchLineIssue: IStitchLineIssue): IStitchLineIssue {
    const copy: IStitchLineIssue = Object.assign({}, stitchLineIssue, {
      createddate: stitchLineIssue.createddate && stitchLineIssue.createddate.isValid() ? stitchLineIssue.createddate.toJSON() : undefined,
      lastupdateddate:
        stitchLineIssue.lastupdateddate && stitchLineIssue.lastupdateddate.isValid() ? stitchLineIssue.lastupdateddate.toJSON() : undefined,
      issuedate: stitchLineIssue.issuedate && stitchLineIssue.issuedate.isValid() ? stitchLineIssue.issuedate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate ? moment(res.body.createddate) : undefined;
      res.body.lastupdateddate = res.body.lastupdateddate ? moment(res.body.lastupdateddate) : undefined;
      res.body.postedDate = res.body.postedDate ? moment(res.body.postedDate) : undefined;
      res.body.issuedate = res.body.issuedate ? moment(res.body.issuedate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((stitchLineIssue: IStitchLineIssue) => {
        stitchLineIssue.createddate = stitchLineIssue.createddate ? moment(stitchLineIssue.createddate) : undefined;
        stitchLineIssue.lastupdateddate = stitchLineIssue.lastupdateddate ? moment(stitchLineIssue.lastupdateddate) : undefined;
      });
    }
    return res;
  }
}
