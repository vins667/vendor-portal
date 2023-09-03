import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';

type EntityResponseType = HttpResponse<IWorkerWorkFlowMaster>;
type EntityArrayResponseType = HttpResponse<IWorkerWorkFlowMaster[]>;

@Injectable({ providedIn: 'root' })
export class WorkerWorkFlowMasterService {
  public resourceUrl = SERVER_API_URL + 'api/worker-work-flow-masters';

  constructor(protected http: HttpClient) {}

  create(workerWorkFlowMaster: IWorkerWorkFlowMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workerWorkFlowMaster);
    return this.http
      .post<IWorkerWorkFlowMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(workerWorkFlowMaster: IWorkerWorkFlowMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workerWorkFlowMaster);
    return this.http
      .put<IWorkerWorkFlowMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWorkerWorkFlowMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWorkerWorkFlowMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(workerWorkFlowMaster: IWorkerWorkFlowMaster): IWorkerWorkFlowMaster {
    const copy: IWorkerWorkFlowMaster = Object.assign({}, workerWorkFlowMaster, {
      createdDate:
        workerWorkFlowMaster.createdDate != null && workerWorkFlowMaster.createdDate.isValid()
          ? workerWorkFlowMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        workerWorkFlowMaster.lastUpdatedDate != null && workerWorkFlowMaster.lastUpdatedDate.isValid()
          ? workerWorkFlowMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((workerWorkFlowMaster: IWorkerWorkFlowMaster) => {
        workerWorkFlowMaster.createdDate = workerWorkFlowMaster.createdDate != null ? moment(workerWorkFlowMaster.createdDate) : null;
        workerWorkFlowMaster.lastUpdatedDate =
          workerWorkFlowMaster.lastUpdatedDate != null ? moment(workerWorkFlowMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
