import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperationMaster } from 'app/shared/model/operation-master.model';

type EntityResponseType = HttpResponse<IOperationMaster>;
type EntityArrayResponseType = HttpResponse<IOperationMaster[]>;

@Injectable({ providedIn: 'root' })
export class OperationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/operation-masters';

  constructor(protected http: HttpClient) {}

  create(operationMaster: IOperationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operationMaster);
    return this.http
      .post<IOperationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(operationMaster: IOperationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operationMaster);
    return this.http
      .put<IOperationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOperationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOperationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(operationMaster: IOperationMaster): IOperationMaster {
    const copy: IOperationMaster = Object.assign({}, operationMaster, {
      createdDate:
        operationMaster.createdDate != null && operationMaster.createdDate.isValid() ? operationMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        operationMaster.lastUpdatedDate != null && operationMaster.lastUpdatedDate.isValid()
          ? operationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((operationMaster: IOperationMaster) => {
        operationMaster.createdDate = operationMaster.createdDate != null ? moment(operationMaster.createdDate) : null;
        operationMaster.lastUpdatedDate = operationMaster.lastUpdatedDate != null ? moment(operationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
