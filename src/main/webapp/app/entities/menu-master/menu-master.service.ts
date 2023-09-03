import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMenuMaster } from 'app/shared/model/menu-master.model';
import { IMenuDetail } from 'app/shared/model/menu-detail.model';
import { Master } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IMenuMaster>;
type EntityArrayResponseType = HttpResponse<IMenuMaster[]>;

@Injectable({ providedIn: 'root' })
export class MenuMasterService {
  public resourceUrl = SERVER_API_URL + 'api/menu-masters';
  public resourceUrlAccess = SERVER_API_URL + 'api/menu-access-masters-authority';
  public resourceUrlQlik = SERVER_API_URL + 'api/menu-masters-qlik';
  public resourceUrlTree = SERVER_API_URL + 'api/menu-access-masters-tree';

  constructor(protected http: HttpClient) {}

  create(menuMaster: IMenuMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(menuMaster);
    return this.http
      .post<IMenuMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(menuMaster: IMenuMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(menuMaster);
    return this.http
      .put<IMenuMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMenuMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findQlik(type: string, url: string): Observable<EntityResponseType> {
    const master = new Master();
    master.id = type;
    master.desc = url;
    return this.http
      .post<IMenuMaster>(`${this.resourceUrlQlik}`, master, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMenuMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  access(authorityName?: string): Observable<HttpResponse<IMenuDetail[]>> {
    return this.http.get<IMenuDetail[]>(`${this.resourceUrlAccess}/${authorityName}`, { observe: 'response' });
  }

  tree(authorityName?: string): Observable<HttpResponse<any[]>> {
    return this.http.get<any[]>(`${this.resourceUrlTree}/${authorityName}`, { observe: 'response' });
  }

  protected convertDateFromClient(menuMaster: IMenuMaster): IMenuMaster {
    const copy: IMenuMaster = Object.assign({}, menuMaster, {
      createdDate: menuMaster.createdDate != null && menuMaster.createdDate.isValid() ? menuMaster.createdDate.toJSON() : null
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
      res.body.forEach((menuMaster: IMenuMaster) => {
        menuMaster.createdDate = menuMaster.createdDate != null ? moment(menuMaster.createdDate) : null;
      });
    }
    return res;
  }
}
