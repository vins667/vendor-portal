import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategoryMaster } from 'app/shared/model/category-master.model';

type EntityResponseType = HttpResponse<ICategoryMaster>;
type EntityArrayResponseType = HttpResponse<ICategoryMaster[]>;

@Injectable({ providedIn: 'root' })
export class CategoryMasterService {
  public resourceUrl = SERVER_API_URL + 'api/category-masters';

  constructor(protected http: HttpClient) {}

  create(categoryMaster: ICategoryMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(categoryMaster);
    return this.http
      .post<ICategoryMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(categoryMaster: ICategoryMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(categoryMaster);
    return this.http
      .put<ICategoryMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICategoryMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICategoryMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(categoryMaster: ICategoryMaster): ICategoryMaster {
    const copy: ICategoryMaster = Object.assign({}, categoryMaster, {
      createdDate: categoryMaster.createdDate != null && categoryMaster.createdDate.isValid() ? categoryMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        categoryMaster.lastUpdatedDate != null && categoryMaster.lastUpdatedDate.isValid() ? categoryMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((categoryMaster: ICategoryMaster) => {
        categoryMaster.createdDate = categoryMaster.createdDate != null ? moment(categoryMaster.createdDate) : null;
        categoryMaster.lastUpdatedDate = categoryMaster.lastUpdatedDate != null ? moment(categoryMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
