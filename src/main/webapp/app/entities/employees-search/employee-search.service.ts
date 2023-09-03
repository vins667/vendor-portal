import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IEmployeeSearch } from 'app/shared/model/employee-search.model';
import { map } from 'rxjs/internal/operators/map';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeSearchService {
  public resourceUrl = SERVER_API_URL + 'api/employee-all-views-custom';
  constructor(protected http: HttpClient) {}

  query(req?: IEmployeeSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEmployeeView[]>(this.resourceUrl, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    return res;
  }
}
