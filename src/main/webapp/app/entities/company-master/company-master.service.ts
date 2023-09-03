import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompanyMaster } from 'app/shared/model/company-master.model';

type EntityResponseType = HttpResponse<ICompanyMaster>;
type EntityArrayResponseType = HttpResponse<ICompanyMaster[]>;

@Injectable({ providedIn: 'root' })
export class CompanyMasterService {
  public resourceUrl = SERVER_API_URL + 'api/company-masters';

  constructor(protected http: HttpClient) {}

  create(companyMaster: ICompanyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(companyMaster);
    return this.http
      .post<ICompanyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(companyMaster: ICompanyMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(companyMaster);
    return this.http
      .put<ICompanyMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICompanyMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICompanyMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(companyMaster: ICompanyMaster): ICompanyMaster {
    const copy: ICompanyMaster = Object.assign({}, companyMaster, {
      createdDate: companyMaster.createdDate != null && companyMaster.createdDate.isValid() ? companyMaster.createdDate.toJSON() : null
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
      res.body.forEach((companyMaster: ICompanyMaster) => {
        companyMaster.createdDate = companyMaster.createdDate != null ? moment(companyMaster.createdDate) : null;
      });
    }
    return res;
  }
}
