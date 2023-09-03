import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { ITdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { map } from 'rxjs/operators';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { ITdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import { ITdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';

type EntityResponseType = HttpResponse<ITdsDeclaration>;
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;

@Injectable({ providedIn: 'root' })
export class TdsQueryService {
  public resourceUrl = SERVER_API_URL + 'api/tds-declarations';
  public customResourceUrl = SERVER_API_URL + 'api/tds-declarations-custom';
  public qryResourceUrl = SERVER_API_URL + 'api/tds-declarations-qry';
  public resourceUrlDownload = SERVER_API_URL + 'api/tds-declaration-uploads-download';
  public resourceUrlEdit = SERVER_API_URL + 'api/tds-declaration-uploads-edit';

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

  downloadXlsx(req?: ITdsDeclarationSearch): Observable<Blob> {
    return this.http.post(`${this.qryResourceUrl + '-export'}`, req, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  downloadPdf(req?: ITdsDeclarationSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-export'}`, req, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  customQuery(): Observable<EntityResponseType> {
    return this.http.get<ITdsDeclaration>(`${this.customResourceUrl}`, { observe: 'response' });
  }

  edit(req?: ITdsDeclarationSearch): Observable<EntityResponseType> {
    return this.http.post<ITdsDeclaration>(this.resourceUrl + '-edit', req, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITdsDeclaration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  lockAll(year: string): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.resourceUrl}-lock-all/${year}`, { observe: 'response' });
  }

  unlockAll(year: string): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.resourceUrl}-unlock-all/${year}`, { observe: 'response' });
  }

  lockSingle(req?: ITdsDeclarationSearch): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.resourceUrl}-single-lock`, req, { observe: 'response' });
  }

  unlockSingle(req?: ITdsDeclarationSearch): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.resourceUrl}-single-unlock`, req, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlDownload}/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  document(req?: ITdsDeclarationUploadSearch): Observable<EntityResponseType> {
    return this.http.post<ITdsDeclarationUpload>(this.resourceUrlEdit, req, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tdsDeclaration: IEmployeeView) => {});
    }
    return res;
  }
}
