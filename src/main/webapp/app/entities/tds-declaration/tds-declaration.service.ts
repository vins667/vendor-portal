import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { ITdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { map } from 'rxjs/operators';
import { IEmployeeView } from 'app/shared/model/employee-view.model';

type EntityResponseType = HttpResponse<ITdsDeclaration>;
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;

@Injectable({ providedIn: 'root' })
export class TdsDeclarationService {
  public resourceUrl = SERVER_API_URL + 'api/tds-declarations';
  public customResourceUrl = SERVER_API_URL + 'api/tds-declarations-custom';
  public qryResourceUrl = SERVER_API_URL + 'api/tds-declarations-qry';
  constructor(protected http: HttpClient) {}

  create(tdsDeclaration: ITdsDeclaration): Observable<EntityResponseType> {
    return this.http.post<ITdsDeclaration>(this.resourceUrl, tdsDeclaration, { observe: 'response' });
  }

  update(tdsDeclaration: ITdsDeclaration): Observable<EntityResponseType> {
    return this.http.put<ITdsDeclaration>(this.resourceUrl, tdsDeclaration, { observe: 'response' });
  }
  query(req?: ITdsDeclarationSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEmployeeView[]>(this.qryResourceUrl, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
  customQuery(): Observable<EntityResponseType> {
    return this.http.get<ITdsDeclaration>(`${this.customResourceUrl}`, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITdsDeclaration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tdsDeclaration: IEmployeeView) => {});
    }
    return res;
  }
}
