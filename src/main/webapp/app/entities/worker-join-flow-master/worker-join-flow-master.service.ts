import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';

type EntityResponseType = HttpResponse<IWorkerJoinFlowMaster>;
type EntityArrayResponseType = HttpResponse<IWorkerJoinFlowMaster[]>;

@Injectable({ providedIn: 'root' })
export class WorkerJoinFlowMasterService {
  public resourceUrl = SERVER_API_URL + 'api/worker-join-flow-masters';

  constructor(protected http: HttpClient) {}

  create(workerJoinFlowMaster: IWorkerJoinFlowMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workerJoinFlowMaster);
    return this.http
      .post<IWorkerJoinFlowMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(workerJoinFlowMaster: IWorkerJoinFlowMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workerJoinFlowMaster);
    return this.http
      .put<IWorkerJoinFlowMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWorkerJoinFlowMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWorkerJoinFlowMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(workerJoinFlowMaster: IWorkerJoinFlowMaster): IWorkerJoinFlowMaster {
    const copy: IWorkerJoinFlowMaster = Object.assign({}, workerJoinFlowMaster, {
      createdDate:
        workerJoinFlowMaster.createdDate != null && workerJoinFlowMaster.createdDate.isValid()
          ? workerJoinFlowMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        workerJoinFlowMaster.lastUpdatedDate != null && workerJoinFlowMaster.lastUpdatedDate.isValid()
          ? workerJoinFlowMaster.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
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
      res.body.forEach((workerJoinFlowMaster: IWorkerJoinFlowMaster) => {
        workerJoinFlowMaster.createdDate = workerJoinFlowMaster.createdDate != null ? moment(workerJoinFlowMaster.createdDate) : null;
        workerJoinFlowMaster.lastUpdatedDate =
          workerJoinFlowMaster.lastUpdatedDate != null ? moment(workerJoinFlowMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
