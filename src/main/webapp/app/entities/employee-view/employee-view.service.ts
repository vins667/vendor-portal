import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IEmployeeSearch } from 'app/shared/model/employee-search.model';
import { IHierarchyBean } from 'app/shared/model/hierarchy-bean.model';

type EntityResponseType = HttpResponse<IEmployeeView>;
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeViewService {
  public resourceUrl = SERVER_API_URL + 'api/employee-views';
  public resourceUrlCustom = SERVER_API_URL + 'api/employee-views-custom';
  public resourceUrlCustomFactory = SERVER_API_URL + 'api/employee-views-custom-factory';
  public resourceUrlHierarchy = SERVER_API_URL + 'api/employee-views-hierarchy';

  constructor(protected http: HttpClient) {}

  create(employeeView: IEmployeeView): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(employeeView);
    return this.http
      .post<IEmployeeView>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(employeeView: IEmployeeView): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(employeeView);
    return this.http
      .put<IEmployeeView>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeView>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByCard(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeView>(`${this.resourceUrl + '-card'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: IEmployeeSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEmployeeView[]>(this.resourceUrlCustom, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFactory(req?: IEmployeeSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEmployeeView[]>(this.resourceUrlCustomFactory, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  hierarchy(): Observable<HttpResponse<IHierarchyBean>> {
    return this.http.get<IHierarchyBean>(this.resourceUrlHierarchy, { observe: 'response' });
  }

  download(cardNo: any): any {
    return this.http.get(`${this.resourceUrlHierarchy}/${cardNo}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(employeeView: IEmployeeView): IEmployeeView {
    const copy: IEmployeeView = Object.assign({}, employeeView, {
      doj: employeeView.doj != null && employeeView.doj.isValid() ? employeeView.doj.format(DATE_FORMAT) : null,
      dob: employeeView.dob != null && employeeView.dob.isValid() ? employeeView.dob.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.doj = res.body.doj != null ? moment(res.body.doj) : null;
      res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((employeeView: IEmployeeView) => {
        employeeView.doj = employeeView.doj != null ? moment(employeeView.doj) : null;
        employeeView.dob = employeeView.dob != null ? moment(employeeView.dob) : null;
      });
    }
    return res;
  }
}
