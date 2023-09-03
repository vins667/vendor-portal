import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';

type EntityResponseType = HttpResponse<IFabricSplFinishMaster>;
type EntityArrayResponseType = HttpResponse<IFabricSplFinishMaster[]>;

@Injectable({ providedIn: 'root' })
export class FabricSplFinishMasterService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-spl-finish-masters';

  constructor(protected http: HttpClient) {}

  create(fabricSplFinishMaster: IFabricSplFinishMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricSplFinishMaster);
    return this.http
      .post<IFabricSplFinishMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fabricSplFinishMaster: IFabricSplFinishMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricSplFinishMaster);
    return this.http
      .put<IFabricSplFinishMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFabricSplFinishMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFabricSplFinishMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fabricSplFinishMaster: IFabricSplFinishMaster): IFabricSplFinishMaster {
    const copy: IFabricSplFinishMaster = Object.assign({}, fabricSplFinishMaster, {
      createdDate:
        fabricSplFinishMaster.createdDate != null && fabricSplFinishMaster.createdDate.isValid()
          ? fabricSplFinishMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        fabricSplFinishMaster.lastUpdatedDate != null && fabricSplFinishMaster.lastUpdatedDate.isValid()
          ? fabricSplFinishMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((fabricSplFinishMaster: IFabricSplFinishMaster) => {
        fabricSplFinishMaster.createdDate = fabricSplFinishMaster.createdDate != null ? moment(fabricSplFinishMaster.createdDate) : null;
        fabricSplFinishMaster.lastUpdatedDate =
          fabricSplFinishMaster.lastUpdatedDate != null ? moment(fabricSplFinishMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
