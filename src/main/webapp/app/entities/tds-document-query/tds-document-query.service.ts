import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITdsDocumentQuery } from './tds-document-query.model';
import { map } from 'rxjs/operators';
import { ITdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { ITdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';

type EntityResponseType = HttpResponse<ITdsDocumentQuery>;
type EntityArrayResponseType = HttpResponse<ITdsDocumentQuery[]>;

@Injectable({ providedIn: 'root' })
export class TdsDocumentQueryService {
  public resourceUrl = SERVER_API_URL + 'api/tds-document-query';
  public resourceUrlXlsx = SERVER_API_URL + 'api/tds-document-query/xlsx';
  public resourceUrlUpload = SERVER_API_URL + 'api/tds-declaration-uploads';
  public resourceUrlActive = SERVER_API_URL + 'api/tds-year-masters-active';
  constructor(protected http: HttpClient) {}

  find(cardNo: String, year: string): Observable<HttpResponse<ITdsDeclarationUpload>> {
    return this.http.get<ITdsDeclarationUpload>(`${this.resourceUrl}/${cardNo}/${year}`, { observe: 'response' });
  }

  save(tdsDeclarationUpload: ITdsDeclarationUpload): Observable<HttpResponse<ITdsDeclarationUpload>> {
    return this.http.post<ITdsDeclarationUpload>(`${this.resourceUrl + '-save'}`, tdsDeclarationUpload, { observe: 'response' });
  }

  query(req?: ITdsDeclarationUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ITdsDocumentQuery[]>(this.resourceUrl, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadXlsx(search: ITdsDeclarationUploadSearch): Observable<Blob> {
    return this.http.post(this.resourceUrlXlsx, search, { responseType: 'blob' });
  }

  download(cardNo: string, year: string): any {
    return this.http.get(`${this.resourceUrlUpload + '-zipdownload'}/${cardNo}/${year}`, {
      headers: new HttpHeaders({}),
      responseType: 'arraybuffer'
    });
  }

  downloadAllZip(req?: ITdsDeclarationUploadSearch): any {
    return this.http.post(this.resourceUrlUpload + '-alldownloadzip', req, {
      headers: new HttpHeaders({}),
      responseType: 'arraybuffer'
    });
  }

  downloadById(id: number): any {
    return this.http.get(`${this.resourceUrlUpload}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  active(): Observable<HttpResponse<ITdsYearMaster>> {
    return this.http.get<ITdsYearMaster>(`${this.resourceUrlActive}`, { observe: 'response' });
  }
  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    return res;
  }
}
