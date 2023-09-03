import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricContentMaster } from 'app/shared/model/fabric-content-master.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IFabricContentMaster>;
type EntityArrayResponseType = HttpResponse<IFabricContentMaster[]>;

@Injectable({ providedIn: 'root' })
export class FabricContentMasterService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-content-masters';

  constructor(protected http: HttpClient) {}

  create(fabricContentMaster: IFabricContentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricContentMaster);
    return this.http
      .post<IFabricContentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fabricContentMaster: IFabricContentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricContentMaster);
    return this.http
      .put<IFabricContentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFabricContentMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFabricContentMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(masterSearch?: IMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IFabricContentMaster[]>(this.resourceUrl + '-search', masterSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fabricContentMaster: IFabricContentMaster): IFabricContentMaster {
    const copy: IFabricContentMaster = Object.assign({}, fabricContentMaster, {
      createdDate:
        fabricContentMaster.createdDate != null && fabricContentMaster.createdDate.isValid()
          ? fabricContentMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        fabricContentMaster.lastUpdatedDate != null && fabricContentMaster.lastUpdatedDate.isValid()
          ? fabricContentMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((fabricContentMaster: IFabricContentMaster) => {
        fabricContentMaster.createdDate = fabricContentMaster.createdDate != null ? moment(fabricContentMaster.createdDate) : null;
        fabricContentMaster.lastUpdatedDate =
          fabricContentMaster.lastUpdatedDate != null ? moment(fabricContentMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
