import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';

type EntityResponseType = HttpResponse<IWorkerForwardTypeMaster>;
type EntityArrayResponseType = HttpResponse<IWorkerForwardTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class WorkerForwardTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/worker-forward-type-masters';
  public resourceUrlForward = SERVER_API_URL + 'api/worker-work-flow-forward-fetch';
  public resourceUrlForwardJoin = SERVER_API_URL + 'api/worker-join-flow-masters-forward-fetch';
  public resourceUrlForwardList = SERVER_API_URL + 'api/worker-forward-type-masters-fetch';
  public resourceUrlForwardListJoin = SERVER_API_URL + 'api/worker-join-flow-masters-users-fetch';

  constructor(protected http: HttpClient) {}

  create(workerForwardTypeMaster: IWorkerForwardTypeMaster): Observable<EntityResponseType> {
    return this.http.post<IWorkerForwardTypeMaster>(this.resourceUrl, workerForwardTypeMaster, { observe: 'response' });
  }

  update(workerForwardTypeMaster: IWorkerForwardTypeMaster): Observable<EntityResponseType> {
    return this.http.put<IWorkerForwardTypeMaster>(this.resourceUrl, workerForwardTypeMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWorkerForwardTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWorkerForwardTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  forward(): Observable<EntityArrayResponseType> {
    return this.http.get<IWorkerForwardTypeMaster[]>(this.resourceUrlForward, { observe: 'response' });
  }

  forwardJoin(): Observable<EntityArrayResponseType> {
    return this.http.get<IWorkerForwardTypeMaster[]>(this.resourceUrlForwardJoin, { observe: 'response' });
  }

  empList(type: string): Observable<EntityArrayResponseType> {
    return this.http.get<IWorkerForwardTypeMaster[]>(`${this.resourceUrlForwardList}/${type}`, { observe: 'response' });
  }

  empListJoin(type: string): Observable<EntityArrayResponseType> {
    return this.http.get<IWorkerJoinFlowMaster[]>(`${this.resourceUrlForwardListJoin}/${type}`, { observe: 'response' });
  }
}
