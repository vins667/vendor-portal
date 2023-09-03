import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { IMasterBean } from 'app/shared/model/master-bean.model';
import { MasterSearch } from 'app/shared/model/master-search.model';

type EntityResponseType = HttpResponse<IFactoryMaster>;
type EntityArrayResponseType = HttpResponse<IFactoryMaster[]>;

@Injectable({ providedIn: 'root' })
export class FactoryMasterService {
  public resourceUrl = SERVER_API_URL + 'api/factory-masters';

  constructor(protected http: HttpClient) {}

  create(factoryMaster: IFactoryMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(factoryMaster);
    return this.http
      .post<IFactoryMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(factoryMaster: IFactoryMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(factoryMaster);
    return this.http
      .put<IFactoryMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFactoryMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  group(): Observable<HttpResponse<IMasterBean[]>> {
    return this.http.get<IMasterBean[]>(this.resourceUrl + '-group', { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFactoryMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  findByGroup(groups: IMasterBean[]): Observable<EntityArrayResponseType> {
    const search = new MasterSearch();
    search.parameters1 = groups.map(division => division.code);
    return this.http.post<IFactoryMaster[]>(this.resourceUrl + '-group', search, { observe: 'response' });
  }

  now(): Observable<EntityArrayResponseType> {
    return this.http
      .get<IFactoryMaster[]>(this.resourceUrl + '-now', { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(factoryMaster: IFactoryMaster): IFactoryMaster {
    const copy: IFactoryMaster = Object.assign({}, factoryMaster, {
      createdDate: factoryMaster.createdDate != null && factoryMaster.createdDate.isValid() ? factoryMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((factoryMaster: IFactoryMaster) => {
        factoryMaster.createdDate = factoryMaster.createdDate != null ? moment(factoryMaster.createdDate) : null;
      });
    }
    return res;
  }
}
