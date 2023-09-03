import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { ITrailMockOperation } from 'app/shared/model/trail-mock-operation.model';
import { ITrailMockSearchOperation } from 'app/shared/model/trail-mock-search.model';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { IWorkerWorkFlowBean } from 'app/shared/model/worker-work-flow-bean.model';
import { IWorkerWorkFlow } from 'app/shared/model/worker-work-flow.model';

type EntityResponseType = HttpResponse<ITrailMockOperation>;
type EntityArrayResponseType = HttpResponse<ITrailMockOperation[]>;

type EntityArrayResponseTypeWR = HttpResponse<IWorkerRecruitment[]>;

@Injectable({ providedIn: 'root' })
export class TrailMockOperationService {
  public resourceUrl = SERVER_API_URL + 'api/trail-mock-operations';
  public resourceUrlCustom = SERVER_API_URL + 'api/trail-mock-operations-custom';
  public resourceUrlWorkflow = SERVER_API_URL + 'api/worker-work-flows';
  constructor(protected http: HttpClient) {}
  create(trailMockOperation: ITrailMockOperation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trailMockOperation);
    return this.http
      .post<ITrailMockOperation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(trailMockOperation: ITrailMockOperation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trailMockOperation);
    return this.http
      .put<ITrailMockOperation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITrailMockOperation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: ITrailMockSearchOperation): Observable<EntityArrayResponseTypeWR> {
    return this.http
      .post<IWorkerRecruitment[]>(this.resourceUrlCustom, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseTypeWR) => this.convertDateArrayFromServerWR(res)));
  }

  workFlow(id: number): Observable<HttpResponse<IWorkerWorkFlowBean>> {
    return this.http.get<IWorkerWorkFlowBean>(`${this.resourceUrlWorkflow}/${id}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  updateFlow(trailMockOperation: IWorkerWorkFlow): Observable<HttpResponse<IWorkerWorkFlow>> {
    const copy = this.convertDateFromClientWorkFlow(trailMockOperation);
    return this.http
      .put<IWorkerWorkFlow>(this.resourceUrlWorkflow, copy, { observe: 'response' })
      .pipe(map((res: HttpResponse<IWorkerWorkFlow>) => this.convertDateFromServerWorkFlow(res)));
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-reports/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(trailMockOperation: ITrailMockOperation): ITrailMockOperation {
    const copy: ITrailMockOperation = Object.assign({}, trailMockOperation, {
      createdDate:
        trailMockOperation.createdDate != null && trailMockOperation.createdDate.isValid() ? trailMockOperation.createdDate.toJSON() : null,
      lastUpdatedDate:
        trailMockOperation.lastUpdatedDate != null && trailMockOperation.lastUpdatedDate.isValid()
          ? trailMockOperation.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromClientWorkFlow(workerWorkFlow: IWorkerWorkFlow): IWorkerWorkFlow {
    const copy: IWorkerWorkFlow = Object.assign({}, workerWorkFlow, {
      authDate: workerWorkFlow.authDate != null && workerWorkFlow.authDate.isValid() ? workerWorkFlow.authDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServerWorkFlow(res: HttpResponse<IWorkerWorkFlow>): HttpResponse<IWorkerWorkFlow> {
    if (res.body) {
      res.body.authDate = res.body.authDate != null ? moment(res.body.authDate) : null;
    }
    return res;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((trailMockOperation: ITrailMockOperation) => {
        trailMockOperation.createdDate = trailMockOperation.createdDate != null ? moment(trailMockOperation.createdDate) : null;
        trailMockOperation.lastUpdatedDate = trailMockOperation.lastUpdatedDate != null ? moment(trailMockOperation.lastUpdatedDate) : null;
      });
    }
    return res;
  }

  protected convertDateArrayFromServerWR(res: EntityArrayResponseTypeWR): EntityArrayResponseTypeWR {
    if (res.body) {
      res.body.forEach((workerRecruitment: IWorkerRecruitment) => {
        workerRecruitment.createdDate = workerRecruitment.createdDate != null ? moment(workerRecruitment.createdDate) : null;
        workerRecruitment.lastUpdatedDate = workerRecruitment.lastUpdatedDate != null ? moment(workerRecruitment.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
