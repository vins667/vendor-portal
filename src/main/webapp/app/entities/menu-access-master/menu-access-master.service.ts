import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMenuAccessMaster } from 'app/shared/model/menu-access-master.model';
import { IMenuSave } from 'app/shared/model/menu-save.model';

type EntityResponseType = HttpResponse<IMenuAccessMaster>;
type EntityArrayResponseType = HttpResponse<IMenuAccessMaster[]>;

@Injectable({ providedIn: 'root' })
export class MenuAccessMasterService {
  public resourceUrl = SERVER_API_URL + 'api/menu-access-masters';

  constructor(protected http: HttpClient) {}

  create(menuAccessMasters: IMenuSave): Observable<HttpResponse<any>> {
    return this.http.post<any>(this.resourceUrl, menuAccessMasters, { observe: 'response' });
  }

  update(menuAccessMaster: IMenuAccessMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(menuAccessMaster);
    return this.http
      .put<IMenuAccessMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMenuAccessMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMenuAccessMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(menuAccessMaster: IMenuAccessMaster): IMenuAccessMaster {
    const copy: IMenuAccessMaster = Object.assign({}, menuAccessMaster, {
      createdDate:
        menuAccessMaster.createdDate != null && menuAccessMaster.createdDate.isValid() ? menuAccessMaster.createdDate.toJSON() : null
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
      res.body.forEach((menuAccessMaster: IMenuAccessMaster) => {
        menuAccessMaster.createdDate = menuAccessMaster.createdDate != null ? moment(menuAccessMaster.createdDate) : null;
      });
    }
    return res;
  }
}
