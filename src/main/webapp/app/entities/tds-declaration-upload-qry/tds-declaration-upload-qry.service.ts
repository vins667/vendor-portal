import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsDeclarationUploadQry } from 'app/shared/model/tds-declaration-upload-qry.model';
import { map } from 'rxjs/operators';
import { ITdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

type EntityResponseType = HttpResponse<ITdsDeclarationUploadQry>;
type EntityArrayResponseType = HttpResponse<ITdsDeclarationUploadQry[]>;

@Injectable({ providedIn: 'root' })
export class TdsDeclarationUploadQryService {
  public resourceUrl = SERVER_API_URL + 'api/tds-declaration-uploads';
  public resourceUrlActive = SERVER_API_URL + 'api/tds-year-masters-active';
  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITdsDeclarationUploadQry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: ITdsDeclarationUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ITdsDeclarationUploadQry[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  download(cardNo: string, year: string): any {
    return this.http.get(`${this.resourceUrl + '-zipdownload'}/${cardNo}/${year}`, {
      headers: new HttpHeaders({}),
      responseType: 'arraybuffer'
    });
  }
  downloadAllZip(req?: ITdsDeclarationUploadSearch): any {
    return this.http.post(this.resourceUrl + '-alldownloadzip', req, {
      headers: new HttpHeaders({}),
      responseType: 'arraybuffer'
    });
  }
  active(): Observable<HttpResponse<ITdsYearMaster>> {
    return this.http.get<ITdsYearMaster>(`${this.resourceUrlActive}`, { observe: 'response' });
  }
  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    return res;
  }
  downloadXlsx(req?: ITdsDeclarationUploadSearch): Observable<Blob> {
    return this.http.post(`${this.resourceUrl + '-report'}`, req, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }
}
