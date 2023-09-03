import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';
import { Master } from 'app/shared/model/master.modal';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';
import { ICutIssueBean } from 'app/shared/model/cut-issue-bean.model';
import { ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';

type EntityResponseType = HttpResponse<IStitchIssuePack>;
type EntityArrayResponseType = HttpResponse<IStitchIssuePack[]>;

@Injectable({ providedIn: 'root' })
export class StitchRecieptPackService {
  public resourceUrl = SERVER_API_URL + 'api/stitch-reciept-packs';
  public resourceUrlDetails = SERVER_API_URL + 'api/stitch-issue-pack-details';
  public resourceUrlFilter = SERVER_API_URL + 'api/stitch-reciept-packs-filter';
  public resourceUrlStyle = SERVER_API_URL + 'api/db2-salesorders-style';
  public resourceUrlBundle = SERVER_API_URL + 'api/cut-plan-bundles';
  public resourceUrlProject = SERVER_API_URL + 'api/projects/';

  constructor(protected http: HttpClient) {}

  create(stitchIssuePack: IStitchIssuePack): Observable<EntityResponseType> {
    return this.http
      .post<IStitchIssuePack>(this.resourceUrl, stitchIssuePack, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(stitchIssuePack: IStitchIssuePack): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stitchIssuePack);
    return this.http
      .put<IStitchIssuePack>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStitchIssuePack>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  post(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStitchIssuePack>(`${this.resourceUrl + '-post'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  bundle(id: number): Observable<HttpResponse<IStitchIssuePackDetails>> {
    return this.http.get<IStitchIssuePackDetails>(`${this.resourceUrlBundle}/${id}`, { observe: 'response' });
  }

  style(projectcode: string): Observable<HttpResponse<ICutIssueBean>> {
    const search = new Master();
    search.code = projectcode;
    return this.http.post<ICutIssueBean>(`${this.resourceUrlStyle}`, search, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IStitchIssuePack[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(search: ICutPlanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IStitchIssuePack[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetails(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(stitchIssuePack: IStitchIssuePack): IStitchIssuePack {
    const copy: IStitchIssuePack = Object.assign({}, stitchIssuePack, {
      createddate: stitchIssuePack.createddate && stitchIssuePack.createddate.isValid() ? stitchIssuePack.createddate.toJSON() : undefined,
      lastupdateddate:
        stitchIssuePack.lastupdateddate && stitchIssuePack.lastupdateddate.isValid() ? stitchIssuePack.lastupdateddate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate ? moment(res.body.createddate) : undefined;
      res.body.lastupdateddate = res.body.lastupdateddate ? moment(res.body.lastupdateddate) : undefined;
      res.body.postedDate = res.body.postedDate ? moment(res.body.postedDate) : undefined;
      res.body.recieptPostedDate = res.body.recieptPostedDate ? moment(res.body.recieptPostedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((stitchIssuePack: IStitchIssuePack) => {
        stitchIssuePack.createddate = stitchIssuePack.createddate ? moment(stitchIssuePack.createddate) : undefined;
        stitchIssuePack.lastupdateddate = stitchIssuePack.lastupdateddate ? moment(stitchIssuePack.lastupdateddate) : undefined;
      });
    }
    return res;
  }
}
