import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFabricUomMaster } from 'app/shared/model/fabric-uom-master.model';

type EntityResponseType = HttpResponse<IFabricUomMaster>;
type EntityArrayResponseType = HttpResponse<IFabricUomMaster[]>;

@Injectable({ providedIn: 'root' })
export class FabricUomMasterService {
  public resourceUrl = SERVER_API_URL + 'api/fabric-uom-masters';

  constructor(protected http: HttpClient) {}

  create(fabricUomMaster: IFabricUomMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricUomMaster);
    return this.http
      .post<IFabricUomMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fabricUomMaster: IFabricUomMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fabricUomMaster);
    return this.http
      .put<IFabricUomMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFabricUomMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFabricUomMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fabricUomMaster: IFabricUomMaster): IFabricUomMaster {
    const copy: IFabricUomMaster = Object.assign({}, fabricUomMaster, {
      createdDate:
        fabricUomMaster.createdDate != null && fabricUomMaster.createdDate.isValid() ? fabricUomMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        fabricUomMaster.lastUpdatedDate != null && fabricUomMaster.lastUpdatedDate.isValid()
          ? fabricUomMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((fabricUomMaster: IFabricUomMaster) => {
        fabricUomMaster.createdDate = fabricUomMaster.createdDate != null ? moment(fabricUomMaster.createdDate) : null;
        fabricUomMaster.lastUpdatedDate = fabricUomMaster.lastUpdatedDate != null ? moment(fabricUomMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
