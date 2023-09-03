import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDocumentMaster } from 'app/shared/model/document-master.model';

type EntityResponseType = HttpResponse<IDocumentMaster>;
type EntityArrayResponseType = HttpResponse<IDocumentMaster[]>;

@Injectable({ providedIn: 'root' })
export class DocumentMasterService {
  public resourceUrl = SERVER_API_URL + 'api/document-masters';
  public resourceUrlCountry = SERVER_API_URL + 'api/document-masters-country';
  public resourceUrlDocument = SERVER_API_URL + 'api/vendor-documents-download';
  public resourceUrlDocumentTransaction = SERVER_API_URL + 'api/vendor-documents-transaction-download';

  constructor(protected http: HttpClient) {}

  create(documentMaster: IDocumentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(documentMaster);
    return this.http
      .post<IDocumentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(documentMaster: IDocumentMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(documentMaster);
    return this.http
      .put<IDocumentMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDocumentMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDocumentMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCountry(countryCode?: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IDocumentMaster[]>(`${this.resourceUrlCountry}/${countryCode}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlDocument}/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  downloadTransaction(id: number): any {
    return this.http.get(`${this.resourceUrlDocumentTransaction}/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(documentMaster: IDocumentMaster): IDocumentMaster {
    const copy: IDocumentMaster = Object.assign({}, documentMaster, {
      createdDate: documentMaster.createdDate != null && documentMaster.createdDate.isValid() ? documentMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        documentMaster.lastUpdatedDate != null && documentMaster.lastUpdatedDate.isValid() ? documentMaster.lastUpdatedDate.toJSON() : null
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
      res.body.forEach((documentMaster: IDocumentMaster) => {
        documentMaster.createdDate = documentMaster.createdDate != null ? moment(documentMaster.createdDate) : null;
        documentMaster.lastUpdatedDate = documentMaster.lastUpdatedDate != null ? moment(documentMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
