import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';

type EntityResponseType = HttpResponse<IFabricSubstractMaster>;
type EntityArrayResponseType = HttpResponse<IFabricSubstractMaster[]>;

@Injectable({ providedIn: 'root' })
export class FabricSubstractMasterService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-substract-masters';

  constructor(protected http: HttpClient) {}

  create(fabricSubstractMaster: IFabricSubstractMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricSubstractMaster);
    return this.http
      .post<IFabricSubstractMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fabricSubstractMaster: IFabricSubstractMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricSubstractMaster);
    return this.http
      .put<IFabricSubstractMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFabricSubstractMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFabricSubstractMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fabricSubstractMaster: IFabricSubstractMaster): IFabricSubstractMaster {
    const copy: IFabricSubstractMaster = Object.assign({}, fabricSubstractMaster, {
      createdDate:
        fabricSubstractMaster.createdDate != null && fabricSubstractMaster.createdDate.isValid()
          ? fabricSubstractMaster.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        fabricSubstractMaster.lastUpdatedDate != null && fabricSubstractMaster.lastUpdatedDate.isValid()
          ? fabricSubstractMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((fabricSubstractMaster: IFabricSubstractMaster) => {
        fabricSubstractMaster.createdDate = fabricSubstractMaster.createdDate != null ? moment(fabricSubstractMaster.createdDate) : null;
        fabricSubstractMaster.lastUpdatedDate =
          fabricSubstractMaster.lastUpdatedDate != null ? moment(fabricSubstractMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
